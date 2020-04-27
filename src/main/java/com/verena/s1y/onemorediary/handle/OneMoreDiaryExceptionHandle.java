package com.verena.s1y.onemorediary.handle;

import com.verena.s1y.onemorediary.constant.Status;
import com.verena.s1y.onemorediary.exception.BaseException;
import com.verena.s1y.onemorediary.pojo.base.ApiResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


//@ControllerAdvice
//@Slf4j
//@ResponseBody
public class OneMoreDiaryExceptionHandle  {

//    @ExceptionHandler(value = Exception.class)
    public ApiResponse exceptionHandle(Exception e){
//        log.error("Exception in" + e.getMessage());
        if (e instanceof BindException){
            BindException ex = (BindException)e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error= errors.get(0);
            String msg = error.getDefaultMessage();
            return ApiResponse.ofException(new BaseException(1000,e.getMessage(),"no token response"));
        }else if (e instanceof BaseException){
            return ApiResponse.ofException((BaseException) e);
        }else {
            return ApiResponse.ofException(new BaseException(Status.UNKNOWN_ERROR,"no token response"));
        }
    }

}
