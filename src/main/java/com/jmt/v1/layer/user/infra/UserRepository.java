package com.jmt.v1.layer.user.infra;

import com.jmt.v1.layer.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUser_id(Long userId);
    Boolean existsByEmail(String email);
}
