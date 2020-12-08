package org.example.model;


import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * http 响应json格式的数据，前后端统一约定为json格式
 * 响应体状态码都是200
 */
//@Getter
//@Setter

public class JSONResponse {

    //业务操作是否成功
    private boolean success;
    //业务操作的消息码，一般来说，出现问题的错误码才有意义，一半给开发人员定位错误
    private String code;
    //业务操做的错误信息，给用户看
    private String message;
    //业务数据：业务操作成功时，执行的操作
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JSONResponse{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
