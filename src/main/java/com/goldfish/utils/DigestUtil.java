package com.goldfish.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldfish.base.error.CommonErrorCode;
import com.goldfish.base.error.ErrorCodeException;

/**
 * 加密工具类
 * @author xiaozhijun
 *
 */
public class DigestUtil {
	
	private static final Logger log = LoggerFactory.getLogger(DigestUtil.class);
	
    public static void checkDigest(String rawData, String sessionKey, String signature) {
    	
        log.info("rawData:{},sessionKey:{},signature:{}", rawData, sessionKey, signature);
        
        // 调用 apache 的公共包进行 SHA1 加密处理
        String sha1 = DigestUtils.sha1Hex((rawData + sessionKey).getBytes());
        boolean equals = sha1.equals(signature);
        if (!equals) {
            throw new ErrorCodeException(CommonErrorCode.SIGNATURE_ERROR);
        }
    }
}
