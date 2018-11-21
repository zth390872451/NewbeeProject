package com.newbee.net.utils;

/**
 * 请求参数断言工具类
 *
 * @creator zheng.th
 */
public abstract class ParameterAssertUtil {

    public static void notNull(Object object,String parameterType,String parameterName) {
        if (object == null) {
            throw new IllegalArgumentException("Required " + parameterType + " parameter \'" + parameterName + "\' is not present");
        }
    }

    public static void isTrue(boolean expression,String message) {
        if(!expression){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isValidProtocolMessage(String value,String parameterName){
        if(value != null){
            if(value.contains(",") || value.contains("#")){
                throw new IllegalArgumentException("Required parameter \'" + parameterName + "\' contains protocol placeholder(\',\',\'#\') ");
            }
        }
    }

}
