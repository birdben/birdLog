package com.birdben.log.bean;

import com.birdben.log.utils.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * @author birdben
 * @version V1.0
 * @name: LogInfo
 * @description 日志信息
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
public class LogInfo {

    /**
     * 开始时间戳
     */
    public long startTimestamp;
    /**
     * 结束时间戳
     */
    public long endTimestamp;
    /**
     * 开始时间
     */
    public Date startDateTime;
    /**
     * 结束时间
     */
    public Date endDateTime;
    /**
     * 执行时间
     */
    public float runTime;
    /**
     * 类名
     */
    public String className;
    /**
     * 方法名
     */
    public String methodName;
    /**
     * 参数
     */
    public List<String> args;
    /**
     * 自定义日志消息
     */
    public String userMessage;

    public LogInfo() {
    }

    public LogInfo(long startTimestamp, long endTimestamp, Date startDateTime, Date endDateTime, float runTime, String className, String methodName, List<String> args, String userMessage) {
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.runTime = runTime;
        this.className = className;
        this.methodName = methodName;
        this.args = args;
        this.userMessage = userMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public float getRunTime() {
        return runTime;
    }

    public void setRunTime(float runTime) {
        this.runTime = runTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("开始时间戳:" + startTimestamp + "\n");
        sb.append("结束时间戳:" + endTimestamp + "\n");
        sb.append("开始时间:" + startDateTime + "\n");
        sb.append("结束时间:" + endDateTime + "\n");
        sb.append("开始时间:" + DateUtils.format(startDateTime, "YYYY-MM-dd HH:mm:ss") + "\n");
        sb.append("结束时间:" + DateUtils.format(endDateTime, "YYYY-MM-dd HH:mm:ss") + "\n");
        sb.append("类名:" + className + "\n");
        sb.append("方法名:" + methodName + "\n");
        sb.append("参数:" + args + "\n");
        sb.append("自定义日志消息:" + userMessage + "\n");
        sb.append("执行时间:" + runTime + " 秒\n");
        return sb.toString();
    }
}
