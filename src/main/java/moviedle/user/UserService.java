package moviedle.user;

import moviedle.movie.Movie;
import moviedle.movie.MovieRepository;
import moviedle.password.PasswordHasher;
import moviedle.password.SecuredPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public UserService(UserRepository userRepository,MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
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

    @Transactional
    public void putMovieToUsersList(String nickname,String title){
       User userFromDB = userRepository.getUserByNickname(nickname);
       userFromDB.addMovie(movieRepository.findMovieByTitle(title));
       userRepository.save(userFromDB);
    }

    public Set<Movie> getFavouriteMovies(String nickname){
        User userFromDB = userRepository.getUserByNickname(nickname);
        return userFromDB.getMovie();
    }

    public boolean isPasswordValid(User user) {
        String nickname = user.getNickname();
        String password = user.getPassword();

        User userFromDB = userRepository.getUserByNickname(nickname);
        SecuredPassword passwordFromDB = new SecuredPassword(userFromDB.getPassword(),userFromDB.getSalt());
        if (Objects.equals(PasswordHasher.getSecuredPasswordBySalt(password, passwordFromDB.getSalt()), passwordFromDB.getPassword())) {
            return true;
        }
        return false;
    }

    public boolean userExistInDB(String nickname) {
        return userRepository.getUserByNickname(nickname) != null;
    }

    private boolean userIsBlank(User user) {
        String nickname = user.getNickname();
        String password = user.getPassword();

        return nickname == null || nickname.isBlank()
                || password == null || password.isBlank();
    }
}
