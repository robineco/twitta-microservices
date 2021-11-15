package de.robinscloud.twittabackend.database.repository;

import de.robinscloud.twittabackend.database.entity.Message;
import de.robinscloud.twittabackend.database.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo  extends CrudRepository<Message, Integer> {
    Message findById(int id);
    List<Message> findAllByUser(User user);
}
