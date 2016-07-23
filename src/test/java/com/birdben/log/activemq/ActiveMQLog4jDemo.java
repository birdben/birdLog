package com.birdben.log.activemq;
 
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
 
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
 
public class ActiveMQLog4jDemo implements MessageListener {
     
    public ActiveMQLog4jDemo() throws Exception {
        // create consumer and listen queue
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        //////////////注意这里JMSAppender只支持TopicDestination，下面会说到////////////////
        Destination topicDestination = session.createTopic("logTopic");
        MessageConsumer consumer = session.createConsumer(topicDestination);
        consumer.setMessageListener(this);
         
        // log a message
        Logger logger = Logger.getLogger(ActiveMQLog4jDemo.class);
        logger.info("Info Log.");
        logger.warn("Warn Log");
        logger.error("Error Log.");
         
        // clean up
        Thread.sleep(1000);
        consumer.close();
        session.close();
        connection.close();
        System.exit(1);
    }
     
    public static void main(String[] args) throws Exception {
        new ActiveMQLog4jDemo();
    }
     
    public void onMessage(Message message) {
        try {
            // receive log event in your consumer
            LoggingEvent event = (LoggingEvent)((ActiveMQObjectMessage)message).getObject();
            System.out.println("Received log [" + event.getLevel() + "]: "+ event.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
}