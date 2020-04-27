package com.verena.s1y.onemorediary.handle

import com.verena.s1y.onemorediary.constant.Status
import com.verena.s1y.onemorediary.exception.BaseException
import com.verena.s1y.onemorediary.pojo.base.ApiResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.validation.BindException
import org.springframework.validation.ObjectError
import org.springframework.web.HttpRequestHandler
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
@Slf4j
@ResponseBody
class OneMoreDiaryExceptionHandles {

    @ExceptionHandler(value = Exception::class)
    fun exceptionHandle(e :Exception, request : HttpServletRequest ) : ApiResponse = when(e){
        is BindException ->{
            val error = e.allErrors[0] as ObjectError
            ApiResponse.ofException(BaseException(1000, error.defaultMessage, request.getAttribute("token").toString()))
        }
        is BaseException ->{
            ApiResponse.ofException(e)
        }
        is RuntimeException ->{
            ApiResponse.ofException(BaseException(3000, e.message, request.getAttribute("token").toString()))
        }
        else -> ApiResponse.ofException(BaseException(Status.UNKNOWN_ERROR, request.getAttribute("token").toString()))
    }

}