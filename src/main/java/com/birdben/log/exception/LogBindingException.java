package com.birdben.log.exception;

/**
 * @author birdben
 * @version V1.0
 * @name LogBindingException
 * @description 绑定异常
 * @github https://github.com/birdben
 * @date 16/7/24 下午12:42
 */
public class LogBindingException extends LogRuntimeException {

    public LogBindingException() {
        super();
    }

    public LogBindingException(String message) {
        super(message);
    }

    public LogBindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogBindingException(Throwable cause) {
        super(cause);
    }

}
