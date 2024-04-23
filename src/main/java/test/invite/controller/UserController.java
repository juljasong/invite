package test.invite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import test.invite.request.Invite;
import test.invite.response.InviteResponse;
import test.invite.response.MessageResponse;
import test.invite.response.UserResponse;
import test.invite.service.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/invite")
    public InviteResponse invite(@RequestBody @Valid Invite request) {
        return new InviteResponse(userService.invite(request));
    }

    @GetMapping("/user/invite/{code}")
    public MessageResponse completeInvite(@PathVariable String code) {
        return new MessageResponse(userService.completeInvite(code));
    }

    @GetMapping("/user/get-user/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return new UserResponse(userService.getUser(id));
    }
}
