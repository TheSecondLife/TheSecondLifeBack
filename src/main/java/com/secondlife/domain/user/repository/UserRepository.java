package com.secondlife.domain.user.repository;

import com.secondlife.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    User findByEmail(String email);
    Optional<User> findByEmail(String email);

    User findUserById(Long Id);
}
