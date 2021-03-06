package slack.repositories;

import org.springframework.data.repository.CrudRepository;
import slack.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
    User findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);
}