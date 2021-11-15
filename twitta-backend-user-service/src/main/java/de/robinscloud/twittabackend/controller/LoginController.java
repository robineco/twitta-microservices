package de.robinscloud.twittabackend.controller;

import de.robinscloud.twittabackend.database.entity.User;
import de.robinscloud.twittabackend.database.repository.UserRepo;
import de.robinscloud.twittabackend.response.DefaultResponse;
import de.robinscloud.twittabackend.utility.Parser;
import de.robinscloud.twittabackend.utility.UserValidation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/user")
public class LoginController {

    private UserRepo userRepo;
    private UserValidation userValidation;

    public LoginController(UserRepo userRepo, UserValidation userValidation) {
        this.userRepo = userRepo;
        this.userValidation = userValidation;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DefaultResponse> getUserById(@PathVariable String id) {
        // Check if provided Id is an integer
        // If not return an 400 error
        if (!Parser.isInteger(id)) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("error", true);
            data.put("message", "Provided id is not a valid id");

            return new ResponseEntity<>(
                    new DefaultResponse(HttpStatus.BAD_REQUEST.value(), data),
                    HttpStatus.BAD_REQUEST
            );
        }
        // Id is an integer
        // Search for User in database
        int userId = Integer.parseInt(id);
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("error", false);
            data.put("user", user.get());

            return new ResponseEntity<>(
                    new DefaultResponse(HttpStatus.OK.value(), data),
                    HttpStatus.OK
            );
        }
        // User not Found
        // return 404
        HashMap<String, Object> data = new HashMap<>();
        data.put("error", true);
        data.put("message", "User not found");
        return new ResponseEntity<>(
                new DefaultResponse(HttpStatus.NOT_FOUND.value(), data),
                HttpStatus.NOT_FOUND
        );
    }

    @PostMapping(path = "")
    public ResponseEntity<DefaultResponse> createNewUser(@RequestBody User user) {
        // Check if there is already a User with provided data
        // If ture, return Conflict error
        if (userValidation.checkIfUserConflict(user)) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("error", true);
            data.put("message", "Username or Email ist already taken");

            return new ResponseEntity<>(
                    new DefaultResponse(HttpStatus.CONFLICT.value(), data),
                    HttpStatus.CONFLICT
            );
        }
        // Check if all necessary data is provided
        // Create new User in database
        if (user.getEmail() != null && user.getPassword() != null && user.getUsername() != null) {
            User createUser = new User(user.getEmail(), user.getPassword(), user.getUsername());
            userRepo.save(createUser);
            User newUser = userRepo.findByEmail(createUser.getEmail());
            log.info("Created new User: {}", newUser);

            HashMap<String, Object> data = new HashMap<>();
            data.put("error", false);
            data.put("user", newUser);
            return new ResponseEntity<>(
                    new DefaultResponse(HttpStatus.OK.value(), data),
                    HttpStatus.OK
            );
        }
        // Something went wrong
        // Return Bad_Request
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<DefaultResponse> loginUser(@RequestBody User user) {
        if (user.getEmail() != null && user.getPassword() != null) {
            User databaseUser = userRepo.findByEmail(user.getEmail());
            if (databaseUser != null) {
                // Check if Password matches
                // This is only a proof of concept
                // Password is saved unencrypted, not the focus of this project
                if (user.getPassword().equals(databaseUser.getPassword())) {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("error", false);
                    data.put("token", databaseUser.hashCode());
                    return new ResponseEntity<>(
                            new DefaultResponse(HttpStatus.OK.value(), data),
                            HttpStatus.OK
                    );
                }
            }
            // Password is not correct
            HashMap<String, Object> data = new HashMap<>();
            data.put("error", true);
            data.put("message", "Invalid E-mail or Password");
            return new ResponseEntity<>(
                    new DefaultResponse(HttpStatus.BAD_REQUEST.value(), data),
                    HttpStatus.BAD_REQUEST
            );
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("error", true);
        data.put("message", "Unspecified error accrued");
        return new ResponseEntity<>(
                new DefaultResponse(HttpStatus.BAD_REQUEST.value(), data),
                HttpStatus.BAD_REQUEST
        );
    }

}
