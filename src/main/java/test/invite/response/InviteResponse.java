package test.invite.response;

import lombok.Builder;
import lombok.Getter;

/**
 * {
 *     "code" : "random_code"
 * }
 */
@Getter
public class InviteResponse {

    private final String code;

    @Builder
    public InviteResponse(String code) {
        this.code = "http://localhost:8080/user/invite/" + code;
    }
    
}
