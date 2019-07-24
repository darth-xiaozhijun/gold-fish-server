package com.goldfish.base.vo;

import java.io.Serializable;

/**
 * 小程序用户登录
 * 返回参数
 * @author xiaozhijun
 *
 */
public class LoginReqVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户信息原始数据
    private String rawData;

    // 用于验证用户信息是否被篡改过
    private String signature;

    // 用户获取 session_key 的 code
    private String code;

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
}
