package ibf2022.paf.assessment.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7

@Service
public class TodoService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Boolean upsertTask(Task taskList){
        User user = new User();
        //insert list of tasks for a user
        //if user does not exist, create the user first, then insert the task
        Boolean isAllTasksInserted = false;

        //check if user exists from the User table
        Optional<User> opt = userRepository.findByUsername(taskList.getTaskList().get(0).getUsername());
        if(opt.isPresent()){
            //user exists
            //insert tasks one by one
            System.out.println("username exists " + taskList.getTaskList().get(0).getUsername());
            //add userId to each task
            String userId = opt.get().getUserId();
            for(Task task: taskList.getTaskList()){
                task.setUserId(userId);
            }
            isAllTasksInserted = insertTasksRepo(taskList, isAllTasksInserted);

            if (isAllTasksInserted){
                System.out.println("All tasks inserted");
                return true;
            } else {
                throw new RuntimeException("Not all tasks inserted, please try again.");
            }
        }

        //user does not exist, need to pass in taskList.getusername into user
        //create user
        //user will input the username. name will be saved as username but with first letter capitalised
        System.out.println("Username does not exist, creating new user " + taskList.getUsername());
        String username = taskList.getUsername();
        
        String firstLetter = username.substring(0,1).toUpperCase();
        String newName = firstLetter + username.substring(1);
        user.setName(newName);
        user.setUserId(username);
        
        //insert the user
        String newUserUUID = userRepository.insertUser(user); //returns uuid
        if (newUserUUID.equalsIgnoreCase("Error")){
            throw new RuntimeException("Error creating new user, please try again.");
        }

        //insert uuid to each task
        for(Task task: taskList.getTaskList()){
            task.setUserId(newUserUUID);
        }

        //insert tasks one by one
        isAllTasksInserted = insertTasksRepo(taskList, isAllTasksInserted);
        if (isAllTasksInserted){
            System.out.println("All tasks inserted");
            return true;
        } else {
            throw new RuntimeException("Not all tasks inserted, please try again.");
        }
    }

    private Boolean insertTasksRepo(Task taskList, Boolean isAllTasksInserted) {
        for(Task task: taskList.getTaskList()){
            isAllTasksInserted = taskRepository.insertSingleTask(task);
        }
        return isAllTasksInserted;
    }
}
