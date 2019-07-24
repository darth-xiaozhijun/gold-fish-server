package com.goldfish.base.vo;

import java.io.Serializable;

/**
 * 小程序用户登录
 * 返回参数
 * @author xiaozhijun
 *
 */
public class LoginResVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String openId;
	
	private String session_key;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	
	
}
