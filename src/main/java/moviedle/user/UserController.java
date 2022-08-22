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
    private final PasswordHasher passwordHasher;

    @Autowired
    public UserController(UserService userService, PasswordHasher passwordHasher){
        this.userService = userService;
        this.passwordHasher = passwordHasher;
    }

    @PostMapping
    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public void requestRegisterNewUser(@RequestBody User user){
        String nickname = user.getNickname();

        if(userService.userCanBeRegistered(user)){
            Logger.registeredNewUser(nickname);
            SecuredPassword securedPassword = passwordHasher.generateSecuredPassword(user.getPassword());
            userService.addNewUser(new User(nickname,securedPassword.getPassword(),securedPassword.getSalt()));
        }else {
            Logger.registrationFailed();
            throw new IllegalStateException("Username is already taken");
        }
    }
}
