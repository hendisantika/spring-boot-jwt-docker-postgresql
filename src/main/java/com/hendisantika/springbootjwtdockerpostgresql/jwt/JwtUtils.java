package com.hendisantika.springbootjwtdockerpostgresql.jwt;

import com.hendisantika.springbootjwtdockerpostgresql.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt-docker-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/23
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
@Component
@Slf4j
public class JwtUtils {

    @Value("${hendi.app.jwtSecret}")
    private String jwtSecret;

    @Value("${hendi.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    private UserRepository userRepository;

    private final RSAPublicKey rsaPublicKey;

    private final RSAPrivateKey rsaPrivateKey;

    public JwtUtils() throws Exception {
        Resource publicKeyResource = new ClassPathResource("public.txt");
        byte[] publicKey = Base64.getDecoder().decode(
                publicKeyResource.getContentAsString(StandardCharsets.UTF_8)
                        .replace("-----BEGIN PUBLIC KEY-----", "")
                        .replace("-----END PUBLIC KEY-----", "")
                        .replaceAll("\\R", "")
        );

        Resource privateKeyResource = new ClassPathResource("private.txt");
        byte[] privateKey = Base64.getDecoder().decode(
                privateKeyResource.getContentAsString(StandardCharsets.UTF_8)
                        .replace("-----BEGIN PRIVATE KEY-----", "")
                        .replace("-----END PRIVATE KEY-----", "")
                        .replaceAll("\\R", "")
        );

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(publicKey));
        rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKey));
    }
}
