package com.metocs.common.controller;

import com.metocs.common.entity.Result;
import com.metocs.common.uils.EmptyUtil;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@ControllerAdvice
public class BaseController {

    private static final Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result errorHandler(HttpServletRequest request, NoHandlerFoundException exception, HttpServletResponse response) {
        String error = "请求："+exception.getRequestURL()+" 发生错误："+exception.getMessage();
        logger.error(error);
        return error(HttpStatus.NOT_FOUND.value(),error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result errorHandler(HttpServletRequest request, HttpRequestMethodNotSupportedException exception, HttpServletResponse response) {
        String error = "请求："+request.getRequestURI()+" 发生错误："+exception.getMessage();
        logger.error(error);
        return error(HttpStatus.METHOD_NOT_ALLOWED.value(),error);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result errorHandler(HttpServletRequest request, HttpMediaTypeNotSupportedException exception, HttpServletResponse response) {
        String error = "请求："+request.getRequestURI()+" 发生错误："+exception.getMessage();
        logger.error(error);
        return error(HttpStatus.NOT_FOUND.value(),error);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result errorHandler(HttpServletRequest request, MissingServletRequestParameterException exception, HttpServletResponse response) {
        String error = "请求："+request.getRequestURI()+" 发生错误："+exception.getMessage();
        logger.error(error);
        return error(HttpStatus.NOT_FOUND.value(),error);
    }


    @ExceptionHandler(value = AuthenticationCredentialsNotFoundException.class)
    public Result errorHandler (HttpServletRequest request, AuthenticationCredentialsNotFoundException exception, HttpServletResponse response) {
        exception.printStackTrace();
        String error = "请求："+request.getRequestURI()+" 发生错误："+exception.getMessage();
        logger.error(error);
        return error(520,"权限验证失败！");
    }

    @ExceptionHandler(value = Exception.class)
    public Result errorHandler (HttpServletRequest request, Exception exception, HttpServletResponse response) {
        exception.printStackTrace();
        String error = "请求："+request.getRequestURI()+" 发生错误："+exception.getMessage();
        logger.error(error);
        return error(520,"服务器加载中！");
    }

    public Result success(){
        return this.success(new HashMap<>());
    }

    public Result success(Object object){
        Result result = new Result();
        result.setCode(Result.SUCCESS);
        result.setMessage("成功");
        result.setData(object);
        return result;
    }

    public Result success(Integer code ,Object object){
        Result result = new Result();
        result.setCode(code);
        result.setData(object);
        result.setMessage("成功");
        return result;
    }

    public Result error(String message){
        Result result = new Result();
        result.setCode(Result.ERROR);
        if (EmptyUtil.not_empty(message)){
            result.setMessage(message);
        } else {
            result.setMessage("失败");
        }
        return result;
    }

    public Result error(Integer code ,String message){
        Result result = new Result();
        result.setCode(code);
        if (EmptyUtil.not_empty(message)){
            result.setMessage(message);
        } else {
            result.setMessage("失败");
        }
        return result;
    }

}
