package pw.react.backend.reactbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.entity.User;
import pw.react.backend.reactbackend.error.ResourceNotFoundException;
import pw.react.backend.reactbackend.respository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
@Autowired
    private UserRepository  urepos;

    public UserService(UserRepository repository) {
    }

    public User check(String login) {
        List<User> users;
        users = (List<User>) urepos.findAll();
        for (User user : users) {
            if (user.getLogin() != null && user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    public User updateUser(User user) {
        Optional<User> existingUser = urepos.findById(user.getId());
        if (existingUser.isPresent()) {
            return urepos.save(user);
        }
        throw new ResourceNotFoundException("User", "id", user.getId());
    }
    public boolean deleteUser(long id){
        boolean result=false;
        if(urepos.existsById(id)){
            urepos.deleteById(id);
            result=true;
        }
        return result;
    }
    
}
