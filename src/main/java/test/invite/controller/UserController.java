package test.invite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import test.invite.request.Invite;
import test.invite.service.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/invite")
    public String invite(@RequestBody @Valid Invite request) {
        String code = userService.invite(request);
        return "http://localhost:8080/user/invite/" + code;
    }

    @GetMapping("/user/invite/{code}")
    public String checkInvite(@PathVariable String code) {
        userService.checkInvite(code);
        return "OK";
    }
}
