package test.invite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import test.invite.request.Invite;
import test.invite.response.InviteResponse;
import test.invite.service.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/invite")
    public InviteResponse invite(@RequestBody @Valid Invite request) {
        return userService.invite(request);
    }

    @GetMapping("/user/invite/{code}")
    public void checkInvite(@PathVariable String code) {
        userService.checkInvite(code);
    }
}
