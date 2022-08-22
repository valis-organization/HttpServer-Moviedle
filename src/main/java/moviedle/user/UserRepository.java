package moviedle.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    //SELECT * FROM student WHERE email = ?
    @Query("SELECT s FROM User s WHERE s.nickname = ?1")
    User findUserByNickname(String nickname);
}
