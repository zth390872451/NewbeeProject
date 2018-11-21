package com.newbee.net.resp;


/**
 * REST response status code
 */
public enum CustomResponseStatus {
	_0(0, "成功"),//成功
	_1(1, "失败");//失败

	private final int code;
	private final String message;
	
	private CustomResponseStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}
	
	/**
	 * Return the enum constant of this type with the specified numeric value.
	 *
	 * @param code
	 *            the numeric value of the enum to be returned
	 * @return the enum constant with the specified numeric value
	 * @throws IllegalArgumentException
	 *             if this enum has no constant for the specified numeric value
	 */
	public static CustomResponseStatus valueOf(int code) {
		for (CustomResponseStatus status : values()) {
			if (status.code == code) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + code + "]");
	}
	
	/**
	 * Return a string representation of this status code.
	 */
	@Override
	public String toString() {
		return Integer.toString(code);
	}
	
}
