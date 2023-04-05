package ibf2022.paf.assessment.server.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO: Task 4

public class Task {


    List<Task> tasks = new ArrayList<Task>();

    private String description;
    private int priority;
    private LocalDateTime dueDate;
    private String username;

    public Task() {
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added " + task);
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
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    
}
