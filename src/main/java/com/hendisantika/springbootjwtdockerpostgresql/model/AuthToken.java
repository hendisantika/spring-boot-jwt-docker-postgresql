package com.hendisantika.springbootjwtdockerpostgresql.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

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
public class AuthToken {
    private final String token;

    private final DecodedJWT decodedJWT;

    public AuthToken(String token) {
        this.token = parseTokenString(token);
        this.decodedJWT = JWT.decode(this.token);
    }

    private String parseTokenString(String token) {
        var tokenArray = token.split(" ");
        return tokenArray[1];
    }

    public String getToken() {
        return token;
    }

    public DecodedJWT getDecodedJWT() {
        return this.decodedJWT;
    }

    public String getPhoneNumber() {
        return getDecodedJWT().getClaim("phoneNumber").asString();
    }

    public String getName() {
        return getDecodedJWT().getClaim("name").asString();
    }

}
