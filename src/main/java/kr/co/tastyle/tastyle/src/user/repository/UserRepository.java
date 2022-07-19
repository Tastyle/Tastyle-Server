package kr.co.tastyle.tastyle.src.user.repository;

import kr.co.tastyle.tastyle.src.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialId(String socialId);

}
