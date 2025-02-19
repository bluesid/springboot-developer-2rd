package me.dylee.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Table(name="users")
    @NoArgsConstructor(access =  AccessLevel.PROTECTED)
    @Getter
    @Entity
    public static class User implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY )
        @Column(name = "id", updatable = false)
        private Long id;

        @Column(name = "email", nullable = false, unique = true)
        private String email;

        @Column(name = "password")
        private String password;

        @Builder
        private User(String email, String password, String auth) {
            this.email = email;
            this.password = password;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority("user"));
        }

        // 사용자의 id를 반환(고유한 값)
        @Override
        public String getUsername() {
            return email;
        }

        // 사용자의 패스워드 반환
        @Override
        public String getPassword() {
            return password;
        }

        // 계정 만료 여부 반환
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        // 계정 잠금 여부
        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        // 패스워드 만료 여부 반환
        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        // 계정 사용 가능 여부 반환
        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
