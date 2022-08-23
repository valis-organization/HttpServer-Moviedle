package moviedle.user;

import moviedle.password.PasswordHasher;
import moviedle.password.SecuredPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerNewUser(User user) {
        String nickname = user.getNickname();
        String password = user.getPassword();

        SecuredPassword securedPassword = PasswordHasher.generateSecuredPassword(password);
        userRepository.save(new User(nickname, securedPassword.getPassword(), securedPassword.getSalt()));
    }

    public void loginUser(User user){
            System.out.println(user.getNickname() + " has logged in."); //todo
    }

    public boolean userCanBeRegistered(User user) {
        if (!userExistInDB(user.getNickname()) & !userIsBlank(user)) {
            return true;
        }
        return false;
    }

    public boolean isPasswordValid(User user) {
        String nickname = user.getNickname();
        String password = user.getPassword();

        User userFromDB = userRepository.findUserByNickname(nickname);
        SecuredPassword passwordFromDB = new SecuredPassword(userFromDB.getPassword(),userFromDB.getSalt());
        if (Objects.equals(PasswordHasher.getSecuredPasswordBySalt(password, passwordFromDB.getSalt()), passwordFromDB.getPassword())) {
            return true;
        }
        return false;
    }

    public boolean userExistInDB(String nickname) {
        return userRepository.findUserByNickname(nickname) != null;
    }

    private boolean userIsBlank(User user) {
        String nickname = user.getNickname();
        String password = user.getPassword();

        return nickname == null || nickname.isBlank()
                || password == null || password.isBlank();
    }
}
