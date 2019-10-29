package pw.react.backend.reactbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.entity.User;
import pw.react.backend.reactbackend.respository.UserRepository;

import java.util.List;

@Service
public class UserService {
@Autowired
    private UserRepository  urepos;
    public User check(String login) {
        List<User> users;
        users = (List<User>) urepos.findAll();
        for (User user : users) {
            if (user.getLogin() != null && user.getLogin().equals(login))
                return user;
        }
        return null;
    }
}
