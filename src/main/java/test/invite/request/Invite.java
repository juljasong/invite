package test.invite.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Invite {

    @NotBlank(message = "이름을 입력해 주십시오.")
    private String name;
    @NotBlank(message = "전화번호를 입력해 주십시오.")
    private String tel;
    @NotBlank(message = "이메일 주소를 입력해 주십시오.")
    private String email;

    @Builder
    public Invite(String name, String tel, String email) {
        this.name = name;
        this.tel = tel;
        this.email = email;
    }

}
