package test.invite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import test.invite.domain.PreUser;
import test.invite.domain.User;
import test.invite.request.Invite;
import test.invite.response.InviteResponse;
import test.invite.response.MessageResponse;
import test.invite.service.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/invite")
    public InviteResponse invite(@RequestBody @Valid Invite request) {
        String code = userService.invite(request);
        return new InviteResponse(code);
    }

    @GetMapping("/user/invite/{code}")
    public MessageResponse completeInvite(@PathVariable String code) {
        userService.completeInvite(code);
        return MessageResponse.builder().message("ok").build();
    }
}
