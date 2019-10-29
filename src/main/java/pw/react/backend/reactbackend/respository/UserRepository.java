package pw.react.backend.reactbackend.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pw.react.backend.reactbackend.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
User findByLogin(String login);
}
