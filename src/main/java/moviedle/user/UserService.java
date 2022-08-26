package moviedle.user;

import moviedle.movie.Movie;
import moviedle.movie.MovieService;
import moviedle.password.PasswordHasher;
import moviedle.password.SecuredPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final MovieService movieService;

    @Autowired
    public UserService(UserRepository userRepository, MovieService movieService) {
        this.userRepository = userRepository;
        this.movieService = movieService;
    }

    //REGISTER & LOGIN
    public void registerNewUser(User user) {
        String nickname = user.getNickname();
        String password = user.getPassword();

        SecuredPassword securedPassword = PasswordHasher.generateSecuredPassword(password);
        userRepository.save(new User(nickname, securedPassword.getPassword(), securedPassword.getSalt()));
    }

    public void loginUser(User user) {
        System.out.println(user.getNickname() + " has logged in."); //todo
    }

    public boolean userCanBeRegistered(User user) {
        if (!userExistInDB(user.getNickname()) & !userIsBlank(user)) {
            return true;
        }
        return false;
    }

    public Movie getMovieByPreferences(String nickname, boolean includeFavMovies) {
        while (true) {
            Movie rndMovie = movieService.getRandomMovie();
            if (includeFavMovies) {
                if (!getDislikedMovies(nickname).contains(rndMovie)) {
                    return rndMovie;
                }
            } else {
                if (!getDislikedMovies(nickname).contains(rndMovie) && !getFavouriteMovies(nickname).contains(rndMovie)) {
                    return rndMovie;
                }
            }

        }
    }

    //FAVOURITE MOVIES
    public Set<Movie> getFavouriteMovies(String nickname) {
        User userFromDB = userRepository.getUserByNickname(nickname);
        return userFromDB.getFavouriteMovies();
    }

    public Movie getMovieFromFavouriteList(String nickname, String title) {
        User userFromDB = userRepository.getUserByNickname(nickname);
        Movie movie = movieService.getMovieByTitle(title);
        if (userFromDB.getFavouriteMovies().contains(movie)) {
            return movie;
        }
        return null;
    }

    @Transactional
    public void putMovieToUsersList(String nickname, String title) {
        User userFromDB = userRepository.getUserByNickname(nickname);
        userFromDB.addMovieToMyList(movieService.getMovieByTitle(title));
        userRepository.save(userFromDB);
    }

    @Transactional
    public void removeMovieFromUsersList(String nickname, String title) {
        User userFromDB = userRepository.getUserByNickname(nickname);
        userFromDB.removeMovieFromMyList(movieService.getMovieByTitle(title));
        userRepository.save(userFromDB);
    }

    //DISLIKED MOVIES
    public Set<Movie> getDislikedMovies(String nickname) {
        User userFromDB = userRepository.getUserByNickname(nickname);
        return userFromDB.getDislikedMovies();
    }

    public Movie getDislikedMovie(String nickname, String title) {
        User userFromDB = userRepository.getUserByNickname(nickname);
        Movie movie = movieService.getMovieByTitle(title);
        if (userFromDB.getDislikedMovies().contains(movie)) {
            return movie;
        }
        return null;
    }

    @Transactional
    public void dislikeMovie(String nickname, String title) {
        User userFromDB = userRepository.getUserByNickname(nickname);
        userFromDB.dislikeMovie(movieService.getMovieByTitle(title));
        userRepository.save(userFromDB);
    }

    @Transactional
    public void unDislikeMovie(String nickname, String title) {
        User userFromDB = userRepository.getUserByNickname(nickname);
        userFromDB.dislikeMovie(movieService.getMovieByTitle(title));
        userRepository.save(userFromDB);
    }


    public boolean isPasswordValid(User user) {
        String nickname = user.getNickname();
        String password = user.getPassword();

        User userFromDB = userRepository.getUserByNickname(nickname);
        SecuredPassword passwordFromDB = new SecuredPassword(userFromDB.getPassword(), userFromDB.getSalt());
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
