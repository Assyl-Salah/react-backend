package pw.react.backend.reactbackend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pw.react.backend.reactbackend.entity.User;
import pw.react.backend.reactbackend.respository.UserRepository;
import pw.react.backend.reactbackend.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/controller")
public class Controller {

    @Autowired
    private UserRepository urepos;
    @Autowired
    private UserService userv;

    @GetMapping("/users")
    public List<User> findallusers() {

        List<User> value = (List<User>) urepos.findAll();
        return value;
    }

    @PostMapping("/create_new_user")
    public String CreatenewUser(@RequestBody User Newuser) {

        if (userv.check(Newuser.getLogin()) == null)
        {
            urepos.save(new User(Newuser.getLogin(), Newuser.getFirst_name(),
                    Newuser.getLast_name(), Newuser.getDate_of_birth(), Newuser.getActive()));
            return "new user is created";
        }
        return "user not created ";
    }

    @GetMapping(value = "/findByLogin/{login}")
    public String retrieveLogin(@PathVariable String login) {
        User user = urepos.findByLogin(login);
        if (user == null)
            return "No user was found";
        return user.toString();
    }
}
