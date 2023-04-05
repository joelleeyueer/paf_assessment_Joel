package ibf2022.paf.assessment.server;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.UserRepository;

@SpringBootApplication
public class ServerApplication{

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	// @Override
    // public void run(String... args) throws Exception {
    //     // Optional<User> opt = userRepository.findByUsername("fred");
    //     // User incomingUser = opt.get();

    //     // System.out.println("incoming user is " + incomingUser);

	// 	// User newUser = new User();
	// 	// newUser.setName("Nadia");
	// 	// newUser.setUsername("nadia");
	// 	// String incomingUUID = userRepository.insertUser(newUser);
	// 	// System.out.println(incomingUUID);
    //     // throw new UnsupportedOperationException("Unimplemented method 'run'");
    // }
}
