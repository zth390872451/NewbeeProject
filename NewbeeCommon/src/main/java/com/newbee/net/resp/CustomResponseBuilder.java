package com.newbee.net.resp;

public class CustomResponseBuilder {

    private static final CustomResponse SUCCESS = new CustomResponse(CustomResponseStatus._0) ;

    private static final CustomResponse FAIL= new CustomResponse(CustomResponseStatus._1) ;

    /**
     *  success with default response body
     */
    public static CustomResponse success(){
        return SUCCESS;
    }

    /**
     * Successful response body with data
     */
    public static CustomResponse success(Object data) {
        return new CustomResponse(CustomResponseStatus._0, data);
    }

    /**
     *  Successful response body with data and message
     */
    public static CustomResponse success(Integer status, String message, Object data) {
        return new CustomResponse(status, message, data);
    }

    /**
     *  Fail  with default response body
     */
    public static CustomResponse fail(){
        return FAIL;
    }

    /**
     *  Fail  response body with status
     */
    public static CustomResponse fail(CustomResponseStatus responseStatus) {
        return new CustomResponse(responseStatus);
    }

    public static CustomResponse fail(String mesage) {
        return new CustomResponse(CustomResponseStatus._1,mesage);
    }

    /**
     *  Fail  response body with status and message
     */
    public static CustomResponse fail(CustomResponseStatus responseStatus, String message) {
        return new CustomResponse(responseStatus, message);
    }

}

