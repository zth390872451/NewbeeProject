package com.newbee.net.exception;

import com.newbee.net.resp.CustomResponseStatus;

/**
 * 资源找不到
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 171460840189918456L;

    public CustomResponseStatus responseStatus;

    public ResourceNotFoundException(CustomResponseStatus responseStatus, Object id) {
        super(String.format(responseStatus.getMessage(), id));
        this.responseStatus = responseStatus;
    }

    public ResourceNotFoundException(String resourceName, Object id) {
        super(resourceName + " with id " + id + " is not found.");

    }

}
