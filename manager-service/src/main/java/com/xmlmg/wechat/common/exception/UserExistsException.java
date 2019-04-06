package com.xmlmg.wechat.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @describe：
 * @version: 1.0
 */
public class UserExistsException extends AuthenticationException {

    public UserExistsException(String msg) {
        super(msg);
    }

}
