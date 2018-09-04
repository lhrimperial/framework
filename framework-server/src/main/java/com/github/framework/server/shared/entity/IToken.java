package com.github.framework.server.shared.entity;

import java.io.Serializable;

/**
 *
 */
public interface IToken extends Serializable {
    /**
     * 解析token
     * @param paramToken
     */
    void tokenToSession(String paramToken);

    /**
     * 获取token令牌
     * @return
     */
    String getTokenParam();
}
