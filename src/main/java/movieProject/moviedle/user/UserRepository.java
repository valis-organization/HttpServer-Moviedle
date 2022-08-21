package movieProject.moviedle.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //SELECT * FROM student WHERE email = ?
    @Query("SELECT s FROM User s WHERE s.nickname = ?1")
    Optional<User> findStudentBySurname(String nickname);
}
