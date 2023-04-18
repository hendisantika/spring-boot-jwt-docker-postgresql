package com.hendisantika.springbootjwtdockerpostgresql.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt-docker-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/23
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    private String message;
}
