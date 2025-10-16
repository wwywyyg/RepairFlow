package org.reapirflow.repairflow.Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Null;
import org.aspectj.bridge.IMessage;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage <T>{
    private Integer code;
    private String message;
    private T data;

    public ResponseMessage(){}

    public ResponseMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Success
    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<>(HttpStatus.OK.value(), "success!!",data);
    }


    public static <T> ResponseMessage<T> success(String message, T data) {
        return new ResponseMessage<>(HttpStatus.OK.value(), message,data);
    }

    // Fail
    public static <T> ResponseMessage<T> fail(String message) {
        return new ResponseMessage<>(HttpStatus.BAD_REQUEST.value(), message,null );
    }

    public Integer getCode() {return code;}
    public void setCode(Integer code) {this.code = code;}
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
    public T getData() {return data;}
    public void setData(T data) {this.data = data;}
}
