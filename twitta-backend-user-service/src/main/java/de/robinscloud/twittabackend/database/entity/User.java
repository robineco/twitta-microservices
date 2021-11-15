package de.robinscloud.twittabackend.database.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique=true)
    private String email;

    @Column(unique=true)
    private String username;

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Message> message;

    @JsonManagedReference
    public List<Message> getMessage() {
        return message;
    }

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}