package se.yrgo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
