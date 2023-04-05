package ibf2022.paf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;

// TODO: Task 6

@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // private static final String INSERT_TASK_SQL = "INSERT INTO task(task_id, task_name, task_description, task_status, user_id) VALUES (?, ?, ?, ?, ?)";

    public void addTaskRepo(Task task){
        task.addTask(task);
    }
}
