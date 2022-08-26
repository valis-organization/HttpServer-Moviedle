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
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public void requestRegisterNewUser(@RequestBody User user){
        String nickname = user.getNickname();

        if(userService.userCanBeRegistered(user)){
            userService.registerNewUser(user);
            Logger.registeredNewUser(nickname);
        }else {
            Logger.registrationFailed();
            throw new IllegalStateException("Username is already taken");
        }
    }

    @PostMapping
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void requestLogin(@RequestBody User user){
        if(userService.isPasswordValid(user) && userService.userExistInDB(user.getNickname())){
            userService.loginUser(user);
        }else {
            throw new IllegalStateException("Password does not match");
        }
    }

    @PutMapping
    @Transactional
    @RequestMapping(value = "/myList/putMovie/{nickname}/{title}",method = RequestMethod.PUT)
    public void putMovieToMyList(@PathVariable("nickname") String nickname,@PathVariable("title") String title){
        userService.putMovieToUsersList(nickname,title);
    }

    @GetMapping
    @RequestMapping(value = "/{nickname}/myList",method = RequestMethod.GET)
    public Set<Movie> getMyList(@PathVariable("nickname") String nickname){
      return userService.getFavouriteMovies(nickname);
    }
}
