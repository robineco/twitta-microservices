package de.robinscloud.twittabackend.utility;

import de.robinscloud.twittabackend.database.entity.User;
import de.robinscloud.twittabackend.database.repository.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UserValidation {

    private UserRepo userRepo;

    public UserValidation(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean checkIfUserConflict(User user) {
        String email = user.getEmail();
        String username = user.getUsername();
        if (userRepo.findByEmail(email) != null || userRepo.findByUsername(username) != null) {
            return true;
        }
        return false;
    }
}
