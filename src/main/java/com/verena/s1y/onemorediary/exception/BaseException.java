package com.verena.s1y.onemorediary.exception;

import com.verena.s1y.onemorediary.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private Integer code;

    private String message;

    private String token;


    public BaseException(Integer code,String message,String token){
        this.code = code;
        this.message = message;
        this.token = token;
    }

    public BaseException(Status status , String token){
        this.code = status.getCode();
        this.message = status.getMessage();
        this.token = token;
    }


}
