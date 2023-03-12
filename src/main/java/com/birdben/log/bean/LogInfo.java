package com.birdben.log.bean;

import com.birdben.log.utils.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * @author birdben
 * @version V1.0
 * @name LogInfo
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
     * 执行时间
     */
    public long cost;
    /**
     * TODO:运行状态
     */
    public Enum status;
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
    public String message;

    public LogInfo() {
    }

    public LogInfo(long startTimestamp, long endTimestamp, long cost, String className, String methodName, List<String> args, String message) {
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.cost = cost;
        this.className = className;
        this.methodName = methodName;
        this.args = args;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public float getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
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
        //sb.append("开始时间:" + DateUtils.format(startDateTime, "YYYY-MM-dd HH:mm:ss") + "\n");
        //sb.append("结束时间:" + DateUtils.format(endDateTime, "YYYY-MM-dd HH:mm:ss") + "\n");
        sb.append("类名:" + className + "\n");
        sb.append("方法名:" + methodName + "\n");
        sb.append("参数:" + args + "\n");
        //sb.append("自定义日志消息:" + userMessage + "\n");
        sb.append("执行时间:" + cost + " 秒\n");
        return sb.toString();
    }
}
