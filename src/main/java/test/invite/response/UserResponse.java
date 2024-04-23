package test.invite.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import test.invite.domain.User;

/**
 * {
 *     "name" : "name",
 *     "email" : "email"
 * }
 */
@Getter
public class UserResponse {

    @Column(nullable = false, length = 40)
    private String name;
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Builder
    public UserResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
    
}
