package ibf2022.paf.assessment.server.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@Controller
@RequestMapping("/task")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TodoService todoService;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView addTask(@RequestParam MultiValueMap<String, String> form) {

        ModelAndView modelAndView = new ModelAndView();

        Task incomingTask = new Task();

        incomingTask = addToTaskList(form);

        incomingTask.printTaskList();

        //save to database
        System.out.println("Saving task to database");
        Boolean upsert = false;
        try {
            upsert = todoService.upsertTask(incomingTask);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (upsert){
            modelAndView.addObject("taskCount", incomingTask.getTaskList().size());
            modelAndView.addObject("username", incomingTask.getTaskList().get(0).getUsername());
            modelAndView.setViewName("result");
            modelAndView.setStatus(HttpStatusCode.valueOf(200));
            return modelAndView;

        } else {
            modelAndView.setViewName("error");
            modelAndView.setStatus(HttpStatusCode.valueOf(500));
            return modelAndView;
        }
        

    }

    public ResponseEntity<String> httpStatus(Boolean success){
        if (success){
            return ResponseEntity.ok("Task added successfully");
        } else {
            return ResponseEntity.badRequest().body("Task not added");
        }
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
