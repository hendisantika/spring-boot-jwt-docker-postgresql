package com.hendisantika.springbootjwtdockerpostgresql.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt-docker-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/23
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String password;
}
