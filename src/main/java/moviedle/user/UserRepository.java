package moviedle.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //SELECT * FROM User WHERE nickname = ?
    @Query("SELECT s FROM User s WHERE s.nickname = ?1")
    User getUserByNickname(String nickname);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.title = ?2 where u.nickname = ?1", nativeQuery = true)
    void putMovie(@Param(value = "nickname") String nickname, @Param(value = "title") String title);
}
