package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUsernameAndPassword(String username, String password);

    User findUserById(String id);
}
