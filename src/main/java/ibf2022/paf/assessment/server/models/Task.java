package ibf2022.paf.assessment.server.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO: Task 4

public class Task {


    private List<Task> taskList = new ArrayList<Task>();

    
    private String description;
    private int priority;
    private LocalDate dueDate;
    private String username;

    public Task() {
    }

    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("Task added " + task);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void printTaskList(){
        System.out.println(taskList);
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Task [description=" + description + ", priority=" + priority + ", dueDate=" + dueDate + ", username="
                + username + "]";
    }

    

    

    
}
