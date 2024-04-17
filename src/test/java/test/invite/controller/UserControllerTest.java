package test.invite.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import test.invite.repository.PreUserRepository;
import test.invite.repository.UserRepository;
import test.invite.request.Invite;
import test.invite.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PreUserRepository preUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void clean() {
        preUserRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("참여자를 초대한다.")
    void invite() throws Exception {
        mockMvc.perform(post("/user/invite")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\": \"테스트\", \"tel\": \"010-1234-5678\", \"email\": \"test@gmail.com\"}")
                )
                .andExpect(status().isOk())
                //.andExpect(content().string("OK"))
                .andDo(print());
    }

    @Test
    @DisplayName("참여자 초대를 수락한다.")
    void checkInvite1() throws Exception {

        Invite invite = Invite.builder()
                .name("테스트")
                .tel("010-2222-3333")
                .email("test@gmail.com")
                .build();

        String code = userService.invite(invite);

        mockMvc.perform(get("/user/invite/" + code))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"message\":\"ok\"}"))
                .andDo(print());
    }

    @Test
    @DisplayName("참여자 초대 실패 - 중복 가입")
    void checkInvite2() throws Exception {

        Invite invite = Invite.builder()
                .name("테스트")
                .tel("010-2222-3333")
                .email("test@gmail.com")
                .build();

        String code = userService.invite(invite);
        userService.completeInvite(code);

        mockMvc.perform(get("/user/invite/" + code))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"code\":\"400\",\"message\":\"ì\u009C í\u009A¨í\u0095\u0098ì§\u0080 ì\u0095\u008Aì\u009D\u0080 ì½\u0094ë\u0093\u009C ì\u009E\u0085ë\u008B\u0088ë\u008B¤.\",\"validation\":{}}"))
                .andDo(print());
    }

}