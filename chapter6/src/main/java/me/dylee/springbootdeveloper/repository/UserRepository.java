package me.dylee.springbootdeveloper.repository;

import me.dylee.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Article.User, Long> {
    Optional<Article.User> findByEmail(String email); // email로 사용자 정보를 가져옴
}
