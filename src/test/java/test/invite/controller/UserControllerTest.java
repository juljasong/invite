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
    void checkInvite() throws Exception {

        Invite invite = Invite.builder()
                .name("테스트")
                .tel("010-2222-3333")
                .email("test@gmail.com")
                .build();

        String code = userService.invite(invite);

        mockMvc.perform(get("/user/invite/" + code))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"))
                .andDo(print());
    }

}