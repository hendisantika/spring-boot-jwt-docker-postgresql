package com.hendisantika.springbootjwtdockerpostgresql.repository;

import com.hendisantika.springbootjwtdockerpostgresql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt-docker-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/18/23
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String email);

    Boolean existsByPhoneNumber(String email);
}
