package pw.react.backend.reactbackend.controller;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pw.react.backend.reactbackend.entity.User;
import pw.react.backend.reactbackend.error.ResourceNotFoundException;
import pw.react.backend.reactbackend.respository.UserRepository;
import pw.react.backend.reactbackend.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

        if (userv.check(Newuser.getLogin()) == null) {
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
            throw new ResourceNotFoundException("User", "login", login);
        return user.toString();
    }

    @GetMapping(value = "/findById/{id}")
    public String retrieveid(@PathVariable long id) {
        Optional<User> user = urepos.findById(id);
        if (!(user).isPresent())
            throw new ResourceNotFoundException("User", "id", id);
        return user.toString();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {
        Optional<User> users = urepos.findById(id);
        if (!((Optional) users).isPresent())
            throw new ResourceNotFoundException("User", "id", id);
          //  return ResponseEntity.notFound().build();
        user.setId(id);
        urepos.save(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id)
    {   if (urepos.findById(id).isPresent())
        urepos.deleteById(id);
    else
    throw new ResourceNotFoundException("User", "id", id);
    }


}
