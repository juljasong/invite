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
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final PreUserRepository preUserRepository;
    private final UserRepository userRepository;

    public String invite(Invite request) {
        String code = UUID.randomUUID().toString();
        PreUser preUser = PreUser.builder()
                .name(request.getName())
                .tel(request.getTel())
                .email(request.getEmail())
                .randomCode(code)
                .valid(true)
                .build();

        preUserRepository.save(preUser);

        return code;
    }

    public void checkInvite(String code) {

        Optional<PreUser> optionalPreUser = preUserRepository.findByRandomCode(code);
        if(optionalPreUser.isEmpty()) {
            throw new ExpiredCodeException();
        }

        PreUser preUser = optionalPreUser.get();
        if(preUser.isValid() == false || LocalDateTime.now().isAfter(preUser.getCreatedAt().plusMinutes(30))) {
            throw new ExpiredCodeException();
        }

        preUser.setValid(false);

        User user = User.builder()
                .name(preUser.getName())
                .tel(preUser.getTel())
                .email(preUser.getEmail())
                .build();

        preUserRepository.save(preUser);
        userRepository.save(user);

    }
}
