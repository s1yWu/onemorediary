package com.verena.s1y.onemorediary.pojo.base;

import com.verena.s1y.onemorediary.constant.Status;
import com.verena.s1y.onemorediary.exception.BaseException;
import lombok.Data;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Data
public class ApiResponse {

    private Integer code;

    private String message;

    private Object data;

    private String Token;


    public static ServletRequestAttributes requestAttributes;

    public ApiResponse() {
    }

    public ApiResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     *
     * @param code
     * @param message
     * @param data
     * @param token
     */
    public ApiResponse(Integer code, String message, Object data, String token) {
        this.code = code;
        this.message = message;
        this.data = data;
        Token = token;
    }

    /**
     *
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ApiResponse of(Integer code, String message , Object data,String token){
        return new ApiResponse(code,message,data,token);
    }
    /**
     *
     * @param data
     * @param
     * @return
     */
    public static ApiResponse ofSuccess(Object data ){
        if (requestAttributes == null)
         requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return ofStatue(Status.OK,data,(String) request.getAttribute("token"));
    }

    /**
     *
     * @param data
     * @param
     * @return
     */
    public static ApiResponse ofSuccess(Object data,String token ){
        return ofStatue(Status.OK,data,token);
    }

    public static ApiResponse ofFail(Status status,Object data){
        if (requestAttributes == null)
            requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return ofStatue(Status.OK,data,(String) request.getAttribute("token"));
    }

    /**
     *
     * @param statue
     * @param data
     * @return
     */
    private static ApiResponse ofStatue(Status statue, Object data,String token) {
        return  of(statue.getCode(),statue.getMessage(),data,token);
    }



    public static <T extends BaseException> ApiResponse ofException(T t, Object data){
        return of(t.getCode() ,t.getMessage() ,data,t.getToken());
    }

    /**
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends BaseException> ApiResponse ofException(T t) {
        return ofException(t, null);
    }
}
