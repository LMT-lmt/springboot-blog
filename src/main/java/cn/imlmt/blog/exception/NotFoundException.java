package cn.imlmt.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//自定义错误
@ResponseStatus(HttpStatus.NOT_FOUND)   //设置错误对应的状态码
public class NotFoundException extends RuntimeException{

    public NotFoundException() { super(); }

    public NotFoundException(String message) { super(message); }

    public NotFoundException(String message, Throwable cause) { super(message, cause); }
}
