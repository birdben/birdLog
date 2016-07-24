package com.birdben.log.exception;

/**
 * @author birdben
 * @version V1.0
 * @name LogException
 * @description 异常处理基础类
 * @github https://github.com/birdben
 * @date 16/7/24 下午12:44
 */
public abstract class LogException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int code = 0;

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     *
     */
    public LogException() {

    }

    public LogException(int code) {
        this.code = code;
    }

    /**
     * @param message
     */
    public LogException(int code, String message) {

        super(message);
        this.code = code;
    }

    public LogException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public LogException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#getCause()
     */
    @Override
    public Throwable getCause() {
        return super.getCause();
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
