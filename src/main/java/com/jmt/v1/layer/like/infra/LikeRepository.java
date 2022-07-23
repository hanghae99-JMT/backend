package com.jmt.v1.layer.like.infra;

import com.jmt.v1.layer.like.domain.Likes;
import com.jmt.v1.layer.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    List<Likes> findAllByUser(User user);
}
