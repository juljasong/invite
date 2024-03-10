package test.invite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.invite.domain.PreUser;

import java.util.Optional;

public interface PreUserRepository extends JpaRepository<PreUser, Long> {
    Optional<PreUser> findByRandomCode(String code);
}
