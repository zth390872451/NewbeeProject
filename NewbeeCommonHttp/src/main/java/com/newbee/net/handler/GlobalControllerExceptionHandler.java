package com.newbee.net.handler;

import com.newbee.net.exception.ResourceNotFoundException;
import com.newbee.net.exception.ServerInternalErrorException;
import com.newbee.net.resp.CustomResponse;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Iterator;

import static com.newbee.net.resp.CustomResponseBuilder.fail;


/**
 * You can pick up any unhandled exceptions here.
 */
@ControllerAdvice
//@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    @ExceptionHandler({AuthenticationException.class})
    public CustomResponse handleAuthenticationException(HttpServletRequest request, AuthenticationException e) {
        logError(request, e);
        return fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler({AuthorizationException.class})
    public CustomResponse handleAuthorizationException(HttpServletRequest request, AuthorizationException e) {
        logError(request, e);
        return fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler({UnauthorizedException.class})
    public CustomResponse handleUnauthorizedException(HttpServletRequest request, UnauthorizedException e) {
        logError(request, e);
        return fail(e.getMessage());
    }

    //Internal Server Error(Known)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler({ServerInternalErrorException.class})
    public CustomResponse handleServerInternalErrorException(HttpServletRequest request, ServerInternalErrorException e) {
        logError(request, e);
        return fail("The server encountered an internal error. Please retry the request.");
    }

    //Internal Server Error(Unknown)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler({RuntimeException.class})
    public CustomResponse handleException(HttpServletRequest request, RuntimeException e) {
        logError(request, e);
        return fail("The server met an unexpected error. Please contact administrators.");
    }

    //Bad Request(非法参数值异常)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public CustomResponse handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
        logError(request, e);
        return fail(e.getMessage());
    }

    //Bad Request(缺少请求参数异常)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CustomResponse handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException e) {
        logError(request, e);
        return fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public CustomResponse handleResourceNotFoundExceptione(HttpServletRequest request, ResourceNotFoundException e) {
        logError(request, e);
        return fail(e.getMessage());
    }

    /**
     * {
     * "timestamp": 1493884206519,
     * "status": 405,
     * "error": "Method Not Allowed",
     * handle": "org.springframework.web.HttpRequestMethodNotSupportedException",
     * "message": "Request method 'GET' not supported",
     * "path": "/api/v1/devices/000000000000000/bind"
     * }
     *
     * @param e
     * @return
     */
    //Method Not Allowed
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public CustomResponse handleHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        logError(request, e);
        return fail(e.getMessage());
    }

    //Unsupported Media Type
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public CustomResponse handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException e) {
        logError(request, e);
        return fail(e.getMessage());
    }

    /**
     * 404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public CustomResponse handleNoHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e) {
        logError(request, e);
        return fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CustomResponse handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        logError(request, e);
        StringBuilder sb = new StringBuilder("请求参数必须满足:");
        Iterator var2 = e.getBindingResult().getAllErrors().iterator();
        while (var2.hasNext()) {
            ObjectError error = (ObjectError) var2.next();
            sb.append("[").append(error.getDefaultMessage()).append("] ");
        }
        return fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(value = ShiroException.class)
    public CustomResponse handleShiroException(HttpServletRequest request, ShiroException e) {
        logError(request, e);
        return fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CustomResponse handleNoHandlerFoundException(HttpServletRequest request, Exception e) {
        logError(request, e);
        return fail(e.getMessage());
    }

    private void logError(HttpServletRequest request, Exception e) {
        LOGGER.error("[请求URI: " + request.getRequestURI() + "]"
                + "[error: " + e.getMessage() + "]", e);
    }

}
