package com.github.framework.server.shared.entity;

import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Random;


/**
 * 登录令牌,使用一次即销毁
 * @author 龙海仁
 * @date 2015年6月23日
 */
public class Token {
	private static final Logger LOGGER = LoggerFactory.getLogger(Token.class);
	private static Random random = new Random(System.currentTimeMillis());
	/**
	 * 用户统一编号，比如：工号，或者昵称等
	 */
	private String userId;
	/**
	 * 会话ID
	 */
	private String sessionId;
	/**
	 * 系统ID
	 */
	private String applicationId;
	
	/**
	 * 标识token的唯一ID
	 */
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return 用户统一编号，比如：工号，或者昵称等
	 */
	public String getUserId() {
		return this.userId;
	}
	/**
	 * 登陆令牌的构造函数
	 * 
	 * @param userId
	 * @param applicationId
	 * @param uuid
	 * @deprecated 从1.1.0版本以后废止
	 */
	public Token(String userId, String applicationId, String uuid) {
		this.userId = userId;
		this.applicationId = applicationId;
		this.uuid=uuid;
	}
	/**
	 * 登陆令牌的构造函数
	 * 
	 * @param userId
	 * @param sessionId
	 * @param applicationId
	 * @param uuid
	 */
	public Token(String userId, String sessionId, String applicationId, String uuid) {
		this.userId = userId;
		this.sessionId = sessionId;
		this.applicationId = applicationId;
		this.uuid=uuid;
	}

	/**
	 * 默认的构造函数
	 */
	public Token() {
		super();
	}

	public String getTokenKey() {
		return this.userId + this.sessionId + this.applicationId+this.uuid;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Token) {
			Token objToken = (Token) obj;
			if (objToken.getTokenKey().equals(this.getTokenKey())) {
				return true;
			}
		}
		return false;
//		if(obj != null){
//			return obj.hashCode()==this.hashCode();
//		}
//		return false;
	}
	@Override 
	public int hashCode() {
        return  this.getTokenKey().hashCode();
    }


	/**
	 * token信息唯一标识
	 * @return
	 */
	public static String genUUID() {
		StringBuffer sb = new StringBuffer(40);
		sb.append(System.currentTimeMillis());
		sb.append("-");
		sb.append(random.nextLong());
		return sb.toString();
	}

	/**
	 * 返回该对象的byte[]数组表示
	 *
	 * @return
	 * @see
	 */
	public byte[] toBytes() {
		try {
			return this.toString().getBytes(CharEncoding.UTF_8);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(),e);
		}
		return null;
	}
}
