package com.xmlmg.wechat.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @describe：
 * @version: 1.0
 */
public class UserNotExistsException extends AuthenticationException {

    public UserNotExistsException(String msg) {
        super(msg);
    }

}
