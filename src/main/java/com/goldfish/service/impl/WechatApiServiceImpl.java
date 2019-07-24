package com.goldfish.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.goldfish.base.error.CommonErrorCode;
import com.goldfish.base.error.ErrorCodeException;
import com.goldfish.base.vo.LoginResVO;
import com.goldfish.service.WechatApiService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class WechatApiServiceImpl implements WechatApiService{
	
	private static final Logger logger = LoggerFactory.getLogger(WechatApiServiceImpl.class);

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.secret}")
    private String secret;

	@Override
	public LoginResVO jscode2session(String code) {
		
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
        
		OkHttpClient okHttpClient = new OkHttpClient();
        
		Request request = new Request.Builder()
                .addHeader("content-type", "application/json")
                .url(String.format(url, appid, secret, code))
                .build();
        try {
        	
            Response execute = okHttpClient.newCall(request).execute();
            if (execute.isSuccessful()) {

            	LoginResVO loginResVO = JSON.parseObject(execute.body().string(), LoginResVO.class);
                
                logger.info("jscode2session get url -> {}, info -> {}", String.format(url, appid, secret, code), JSON.toJSONString(loginResVO));
                return loginResVO;
          
            } else {
            
            	logger.error("jscode2session authorize error -> {}", code);
                throw new ErrorCodeException(CommonErrorCode.OBTAIN_OPENID_ERROR);
            }

        } catch (IOException e) {
            
        	logger.error("jscode2session authorize error -> {}", code, e);
            throw new ErrorCodeException(CommonErrorCode.OBTAIN_OPENID_ERROR);
        }
	}

}
