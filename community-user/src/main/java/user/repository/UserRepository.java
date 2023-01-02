package user.repository;

import common.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUsernameAndPassword(String username, String password);

    User findUserById(String id);
}
