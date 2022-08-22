package moviedle.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public boolean userCanBeRegistered(User user) {
        if (!userExistInDB(user.getNickname()) & !userIsBlank(user)) {
            return true;
        }
        return false;
    }

    private boolean userExistInDB(String nickname) {
        return userRepository.findUserByNickname(nickname) != null;
    }

    private boolean userIsBlank(User user) {
        String nickname = user.getNickname();
        String password = user.getPassword();

        return nickname == null || nickname.isBlank()
                || password == null || password.isBlank();
    }
}
