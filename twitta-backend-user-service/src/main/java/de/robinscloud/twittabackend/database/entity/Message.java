package de.robinscloud.twittabackend.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @JsonBackReference
    public User getUser() {
        return user;
    }

    public Message(String message, User user) {
        this.message = message;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", likes=" + likes +
                ", user=" + user +
                '}';
    }
}
