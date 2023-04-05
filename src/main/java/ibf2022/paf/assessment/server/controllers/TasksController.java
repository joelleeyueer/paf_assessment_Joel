package ibf2022.paf.assessment.server.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.repositories.TaskRepository;

// TODO: Task 4, Task 8

@RestController
@RequestMapping("/task")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> addTask(@RequestParam MultiValueMap<String, String> form) {

        Task incomingTask = new Task();

        incomingTask = addToTaskList(form);


        incomingTask.printTaskList();
        return ResponseEntity.ok("Task added");
        

    }

    public Task addToTaskList(MultiValueMap<String, String> form){

        Task incomingTask = new Task();

        // username: fred
        // description-0: eat bfast
        // priority-0: 1
        // dueDate-0: 2023-04-04
        // description-1: take a shit
        // priority-1: 2
        // dueDate-1: 2023-04-06
        // description-2: eat again
        // priority-2: 1
        // dueDate-2: 2023-04-04

        String userName = form.getFirst("username");

        for (int i = 0; i < form.size(); i++){
            String buildDescription = "description-" + i;
            String buildPriority = "priority-" + i;
            String buildDueDate = "dueDate-" + i;

            String iterateDescription = form.getFirst(buildDescription);
            String iteratePriority = form.getFirst(buildPriority);
            String iterateDueDate = form.getFirst(buildDueDate);

            //check if null, if yes, return null
            if (iterateDescription==null || iteratePriority==null || iterateDueDate==null){
                break;
            }

            Task iterateTask = new Task();
            iterateTask.setUsername(userName);
            iterateTask.setDescription(iterateDescription);
            iterateTask.setPriority(Integer.parseInt(iteratePriority));
            iterateTask.setDueDate(LocalDate.parse(iterateDueDate));

            //add the task to tasklist
            incomingTask.addTask(iterateTask);
        }

        return incomingTask;

    }

    
}
