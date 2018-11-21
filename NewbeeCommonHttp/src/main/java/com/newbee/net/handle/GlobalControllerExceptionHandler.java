package com.newbee.net.handle;

import com.newbee.net.exception.ResourceNotFoundException;
import com.newbee.net.exception.ServerInternalErrorException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Iterator;

import static com.newbee.net.resp.CustomResponseBuilder.fail;


/**
 * You can pick up any unhandled exceptions here.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);


    //Internal Server Error(Known)
    @ExceptionHandler({ServerInternalErrorException.class})
    public ResponseEntity<?> handleServerInternalErrorException(HttpServletRequest request, ServerInternalErrorException e) {
        logError(request, e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(fail("The server encountered an internal error. Please retry the request."));
    }

    //Internal Server Error(Unknown)
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleException(HttpServletRequest request, RuntimeException e) {
        logError(request, e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(fail("The server met an unexpected error. Please contact administrators."));
    }

    //Bad Request(非法参数值异常)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
        logError(request, e);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fail(e.getMessage()));
    }

    //Bad Request(缺少请求参数异常)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException e) {
        logError(request, e);
        return ResponseEntity
                //.status(HttpStatus.BAD_REQUEST)
                .status(HttpStatus.OK)
                .body(fail(e.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundExceptione(HttpServletRequest request, ResourceNotFoundException e) {
        logError(request, e);
        if (e.responseStatus != null) {
            return ResponseEntity
                    //.status(HttpStatus.NOT_FOUND)
                    .status(HttpStatus.OK)
                    .body(fail(e.responseStatus, e.getMessage()));
        } else {
            return ResponseEntity
                    //.status(HttpStatus.NOT_FOUND)
                    .status(HttpStatus.OK)
                    .body(fail(e.getMessage()));

        }
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
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        logError(request, e);
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(fail( e.getMessage()));
    }

    //Unsupported Media Type
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException e) {
        logError(request, e);
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(fail( e.getMessage()));
    }

    /**
     * 404
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<?> handleNoHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e) {
        logError(request, e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(fail(e.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        logError(request, e);
        StringBuilder sb = new StringBuilder("请求参数必须满足:");
        Iterator var2 = e.getBindingResult().getAllErrors().iterator();
        while (var2.hasNext()) {
            ObjectError error = (ObjectError) var2.next();
            sb.append("[").append(error.getDefaultMessage()).append("] ");
        }
        return ResponseEntity
                //.status(HttpStatus.BAD_REQUEST)
                .status(HttpStatus.OK)
                .body(fail(sb.toString()));
    }

    private void logError(HttpServletRequest request, Exception e) {
        LOGGER.error("[URI: " + request.getRequestURI() + "]"
                + "[error: " + e.getMessage() + "]", e);
    }

}
