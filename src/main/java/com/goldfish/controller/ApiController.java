package com.goldfish.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.goldfish.base.vo.Result;

@RestController
@RequestMapping("/api")
public class ApiController {

	@RequestMapping("/login.do")
	public String login(){
		Result<String> result = new Result<>();
		result.setSuccess(true);
		return JSON.toJSONString(result);
	}
}
