package me.dylee.springbootdeveloper;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자
@AllArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public void changeName(String name) {
        this.name = name;
    }
}
