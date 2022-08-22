package moviedle.user;

import javax.persistence.*;

@Table
@Entity
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String nickname;
    private String password;
    private String salt;

    //TABLE
    public User(Long id, String nickname, String password, String salt) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.salt = salt;
    }

    //Saving to dB
    public User(String nickname, String password, String salt) {
        this.nickname = nickname;
        this.password = password;
        this.salt = salt;
    }

    //Received user
    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public User() {

    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
}
