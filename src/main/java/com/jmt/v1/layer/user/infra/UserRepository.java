package com.jmt.v1.layer.user.infra;

import com.jmt.v1.layer.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String userEmail);

    User findByUser_id(Long userId);
}
