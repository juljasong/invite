package test.invite.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PreUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 40)
    private String name;
    @Column(nullable = false, length = 20)
    private String tel;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(length = 36)
    private String randomCode;
    @Column(nullable = false)
    private boolean valid;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Builder
    public PreUser(String name, String tel, String email, String randomCode, boolean valid) {
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.randomCode = randomCode;
        this.valid = valid;
        this.createdAt = LocalDateTime.now();
    }

}
