# birdLog是一个基于ELK的开源日志收集和分析项目
# 系统架构
Nginx -> Logstash -> Redis -> ElasticSearch -> Kibana

# Release History:
## v1.0
### 支持SpringAOP切面方式自定义日志格式处理

#### 支持如下三种方式Log传参处理
#### 测试URL
##### 方式一:默认Log注解默认使用当前Class的日志处理方法(已经废弃)
##### 方式二:多参数方式处理日志
- http://127.0.0.1:8080/birdLog/aop/aopHandlerLogInThisClassWithMultipleParam?name=birdben&age=20&job=programmer&website=github.com/birdben
- http://127.0.0.1:8080/birdLog/aop/aopHandlerLogWithMultipleParam?name=birdben&age=20&job=programmer&website=github.com/birdben

##### 方式三:Map参数方式处理日志
- http://127.0.0.1:8080/birdLog/aop/aopHandlerLogInThisClassWithMapParam?name=birdben&age=20&job=programmer&website=github.com/birdben
- http://127.0.0.1:8080/birdLog/aop/aopHandlerLogWithMapParam?name=birdben&age=20&job=programmer&website=github.com/birdben

### 待处理的List
- 创建一个LogHandler接口，提供一个Map参数，其他自定义的LogHandler只需要实现此接口就可以定义自己的日志输出格式
- 创建线程异步写入日志文件
- 按照不同的格式输出到log4j
- 搭建ELK环境
- Logstash根据不同的日志格式模板创建不同的ES索引