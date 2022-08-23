package moviedle.user;

import moviedle.helpers.Logger;
import moviedle.password.PasswordHasher;
import moviedle.password.SecuredPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
