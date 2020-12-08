package org.example.exception;

/**
 * 自定义异常类：业务代码抛异常，或者其他异常
 */

public class AppException extends RuntimeException{
    private String code;
    public AppException(String code,String message) {
        super(message);
        this.code=code;
        //this.AppException(message,code,null)
    }

    public AppException(String code,String message, Throwable cause) {
        super(message, cause);
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
