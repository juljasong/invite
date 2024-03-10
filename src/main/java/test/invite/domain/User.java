package test.invite.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 40)
    private String name;
    @Column(nullable = false, length = 20)
    private String tel;
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public User(String name, String tel, String email) {
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }
}
