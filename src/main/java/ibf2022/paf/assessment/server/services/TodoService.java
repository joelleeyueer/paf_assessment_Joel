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
    public Boolean upsertTask(User user, Task taskList){
        //insert list of tasks for a user
        //if user does not exist, create the user first, then insert the task
        Boolean isAllTasksInserted = false;

        //check if user exists
        Optional<User> opt = userRepository.findByUsername(taskList.getUsername());
        if(opt.isPresent()){
            //user exists
            //insert tasks one by one
            isAllTasksInserted = insertTasksRepo(taskList, isAllTasksInserted);

            if (isAllTasksInserted){
                return true;
            } else {
                throw new RuntimeException("Not all tasks inserted, please try again.");
            }
        }

        //user does not exist
        //create user
        //user will input the username. name will be saved as username but with first letter capitalised
        String username = user.getUsername();
        String name = null;
        if (user.getName() == null){
            String firstLetter = username.substring(0,1).toUpperCase();
            String newName = firstLetter + username.substring(1);
            user.setName(newName);
            name = newName;
        } else {
            name = user.getName();
        }
        
        User incomingUser = new User();
        userRepository.insertUser(incomingUser);

        //insert tasks one by one
        isAllTasksInserted = insertTasksRepo(taskList, isAllTasksInserted);
        if (isAllTasksInserted){
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
