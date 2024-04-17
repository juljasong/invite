package test.invite.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import test.invite.domain.PreUser;
import test.invite.domain.User;
import test.invite.exception.ExpiredCodeException;
import test.invite.repository.PreUserRepository;
import test.invite.repository.UserRepository;
import test.invite.request.Invite;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final PreUserRepository preUserRepository;
    private final UserRepository userRepository;

    public String invite(Invite request) {
        String code = UUID.randomUUID().toString();
        savePreUser(request, code);
        return code;
    }

    public String completeInvite(String code) {
        PreUser preUser = updateIsValidPreUser(code);
        saveUser(preUser);
        return "ok";
    }

    private void savePreUser(Invite request, String code) {
        PreUser preUser = PreUser.builder()
                .name(request.getName())
                .tel(request.getTel())
                .email(request.getEmail())
                .randomCode(code)
                .valid(true)
                .build();
        preUserRepository.save(preUser);
    }

    private PreUser updateIsValidPreUser(String code) {
        PreUser preUser = preUserRepository.findByRandomCode(code)
                .filter(PreUser::isValid)
                .filter(user -> !LocalDateTime.now().isAfter(user.getCreatedAt().plusMinutes(30)))
                .orElseThrow(ExpiredCodeException::new);
        preUser.setValid(false);

        return preUserRepository.save(preUser);
    }

    private void saveUser(PreUser preUser) {
        User user = User.builder()
                .name(preUser.getName())
                .tel(preUser.getTel())
                .email(preUser.getEmail())
                .build();

        userRepository.save(user);
    }


}
