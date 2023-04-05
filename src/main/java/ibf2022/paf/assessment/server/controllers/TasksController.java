package ibf2022.paf.assessment.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.repositories.TaskRepository;

// TODO: Task 4, Task 8

@RestController
@RequestMapping("/task")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addTask(@RequestBody Task task) {

        System.out.println("Task received " + task);

        if (task.getDescription() == null || task.getDescription().isEmpty()) {
            return ResponseEntity.badRequest().body("Description is required");
        }

        if (task.getPriority() < 1 || task.getPriority() > 3) {
            return ResponseEntity.badRequest().body("Priority must be between 1 and 3");
        }

        taskRepository.addTaskRepo(task);

        return ResponseEntity.ok("Task added");
        

        

        
    }

    
}
