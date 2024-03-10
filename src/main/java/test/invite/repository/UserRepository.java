package test.invite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.invite.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
