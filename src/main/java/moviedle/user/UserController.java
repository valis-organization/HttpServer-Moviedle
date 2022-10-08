package moviedle.user;

import moviedle.helpers.Logger;
import moviedle.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    private void requestRegisterNewUser(@RequestBody User user) {
        String nickname = user.getNickname();

        if (userService.userCanBeRegistered(user)) {
            userService.registerNewUser(user);
            Logger.registeredNewUser(nickname);
        } else {
            Logger.registrationFailed();
            throw new IllegalStateException("Username is already taken");
        }
    }

 /*   @PostMapping
    @RequestMapping(value = "/tokenSignIn", method = RequestMethod.POST)
    private void requestGoogleToken(@RequestBody String tokenId) {
        String id = tokenId;
        System.out.println("RECEIVED GOOGLE ID TOKEN = " + id);
    }*/

    @PostMapping
    @RequestMapping(value = "/tokenSignIn/{tokenId}", method = RequestMethod.POST)
    private String requestGoogleToken(@PathVariable("tokenId") String tokenId) {
        System.out.println("RECEIVED GOOGLE ID TOKEN = " + tokenId);
        TokenVerifier tokenVerifier = new TokenVerifier();
        tokenVerifier.authenticate(tokenId);
        return "asd";
    }

    @PostMapping
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private void requestLogin(@RequestBody User user) {
        if (userService.isPasswordValid(user) && userService.userExistInDB(user.getNickname())) {
            userService.loginUser(user);
        } else {
            throw new IllegalStateException("Password does not match");
        }
    }

    @PutMapping
    @Transactional
    @RequestMapping(value = "/myList/{nickname}/putMovie/{title}", method = RequestMethod.PUT)
    private void putMovieToMyList(@PathVariable("nickname") String nickname, @PathVariable("title") String title) {
        if (userService.getDislikedMovie(nickname, title) != null) {
            userService.unDislikeMovie(nickname, title);
        }
        userService.putMovieToUsersList(nickname, title);
    }

    @DeleteMapping
    @RequestMapping(value = "/myList/{nickname}/removeMovie/{title}", method = RequestMethod.DELETE)
    private void removeMovieFromMyList(@PathVariable("nickname") String nickname, @PathVariable("title") String title) {
        userService.removeMovieFromUsersList(nickname, title);
    }

    @GetMapping
    @RequestMapping(value = "/myList/{nickname}", method = RequestMethod.GET)
    private Set<Movie> getMyList(@PathVariable("nickname") String nickname) {
        return userService.getFavouriteMovies(nickname);
    }

    @PutMapping
    @Transactional
    @RequestMapping(value = "/dislikedMovies/{nickname}/putMovie/{title}", method = RequestMethod.PUT)
    private void dislikeMovie(@PathVariable("nickname") String nickname, @PathVariable("title") String title) {
        if (userService.getMovieFromFavouriteList(nickname, title) != null) {
            userService.removeMovieFromUsersList(nickname, title);
        }
        userService.dislikeMovie(nickname, title);
    }

    @DeleteMapping
    @RequestMapping(value = "/dislikedMovies/{nickname}/removeMovie/{title}", method = RequestMethod.DELETE)
    private void removeMovieFromDislikedList(@PathVariable("nickname") String nickname, @PathVariable("title") String title) {
        userService.unDislikeMovie(nickname, title);
    }

    @GetMapping
    @RequestMapping(value = "/usersPreferences/{nickname}/randomMovie/{boolean}", method = RequestMethod.GET)
    private Movie getRandomMovieByPreferences(@PathVariable("nickname") String nickname, @PathVariable("boolean") boolean includeFavouriteMovies) {
        return userService.getMovieByPreferences(nickname, includeFavouriteMovies);
    }
}
