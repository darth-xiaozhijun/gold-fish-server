package com.goldfish.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.goldfish.base.error.CommonErrorCode;
import com.goldfish.base.error.ErrorCodeException;
import com.goldfish.base.vo.LoginReqVO;
import com.goldfish.base.vo.LoginResVO;
import com.goldfish.base.vo.Result;
import com.goldfish.base.vo.Token;
import com.goldfish.service.WechatApiService;
import com.goldfish.utils.DigestUtil;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	private static final Logger log = LoggerFactory.getLogger(ApiController.class);

	@Resource
	private WechatApiService wechatApiService;

	@RequestMapping("/login.do")
	public Result<Token> login(@RequestBody LoginReqVO loginReqVO){
		
		Result<Token> result = new Result<>();
		
		try {
			
            log.info("login loginReqVO : {}", JSON.toJSONString(loginReqVO));
            // 使用 code 调用微信 API 获取 session_key 和 openid
            LoginResVO loginResVO = wechatApiService.jscode2session(loginReqVO.getCode());
            log.info("login get loginResVO : {}", loginResVO);

            // 检验传递过来的使用户信息是否合法
            DigestUtil.checkDigest(loginReqVO.getRawData(), loginResVO.getSession_key(), loginReqVO.getSignature());
            
            //TODO: 储存 token
            //生成token，用于自定义登录态，这里的存储逻辑比较复杂，放到下一讲
            Token data = new Token();
            data.setToken(UUID.randomUUID().toString());
            
            result.setStatus("200");
            result.setBody(data);
            result.setSuccess(true);
            
            return result;
        } catch (ErrorCodeException e) {
        	
        	result.setStatus("400");
        	result.setSuccess(false);
            log.error("login error, info : {}", JSON.toJSONString(loginReqVO), e.getMessage());
            
        } catch (Exception e) {
        	
        	result.setStatus(CommonErrorCode.UNKOWN_ERROR.getCode()+"");
        	result.setMsg(CommonErrorCode.UNKOWN_ERROR.getMessage());
            log.error("login error, info : {}", JSON.toJSONString(loginReqVO), e);
        }
		
		return result;
	}
}
