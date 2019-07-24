package com.goldfish.base.error;

/**
 * 错误码异常
 * @author xiaozhijun
 *
 */
public class ErrorCodeException extends RuntimeException implements IErrorCode {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer code;

    private String message;

    public ErrorCodeException(IErrorCode iErrorCode) {
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorCodeException [code=" + code + ", message=" + message + "]";
	}

}
