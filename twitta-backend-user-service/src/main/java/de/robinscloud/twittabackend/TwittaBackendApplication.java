package de.robinscloud.twittabackend;

import de.robinscloud.twittabackend.database.entity.Message;
import de.robinscloud.twittabackend.database.entity.User;
import de.robinscloud.twittabackend.database.repository.MessageRepo;
import de.robinscloud.twittabackend.database.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwittaBackendApplication implements CommandLineRunner {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private MessageRepo messageRepo;

	public static void main(String[] args) {
		SpringApplication.run(TwittaBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User(
//				"test@user.de",
//				"password",
//				"test-user"
//		);
//		userRepo.save(user);
//		Message message = new Message(
//				"Test message",
//				user
//		);
//		messageRepo.save(message);
	}
}
