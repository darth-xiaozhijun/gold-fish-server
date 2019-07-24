package com.goldfish.service;

import com.goldfish.base.vo.LoginResVO;

public interface WechatApiService {

	/**
	 * 小程序用户登录
	 * @param code
	 * @return
	 */
	LoginResVO jscode2session(String code);
}
