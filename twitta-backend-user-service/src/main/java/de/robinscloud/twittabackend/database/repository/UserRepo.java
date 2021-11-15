package de.robinscloud.twittabackend.database.repository;

import de.robinscloud.twittabackend.database.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUserId(int id);
    User findByUsername(String username);
    User findByEmail(String email);
}
