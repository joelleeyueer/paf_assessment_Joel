package ibf2022.paf.assessment.server;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.repositories.UserRepository;
import ibf2022.paf.assessment.server.services.TodoService;

@SpringBootApplication
public class ServerApplication{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TodoService todoService;
	

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
		
	// 	//testing, username exists
	// 	// Task oneTask = new Task();
	// 	// oneTask.setDescription("eat");
	// 	// oneTask.setPriority(0);
	// 	// oneTask.setDueDate(LocalDate.now());
	// 	// Task taskList = new Task();
	// 	// taskList.setUsername("nadia");
	// 	// taskList.addTask(oneTask);
		
	// 	// todoService.upsertTask(taskList);
    //     // throw new UnsupportedOperationException("Unimplemented method 'run'");
    // }
}
