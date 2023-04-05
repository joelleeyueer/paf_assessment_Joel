package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.User;

// TODO: Task 3

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_BY_USERNAME_SQL = "SELECT * FROM user WHERE username = ?";
    private static final String INSERT_USER_SQL = "INSERT INTO user(user_id, username, name) VALUES (?, ?, ?)";

    public Optional<User> findByUsername(String username) {
        try {
            User incomingUser = jdbcTemplate.queryForObject
            (FIND_BY_USERNAME_SQL, BeanPropertyRowMapper.newInstance(User.class), username);
            return Optional.of(incomingUser);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public String insertUser(User user) {
        user.setUserId(generateUUID());
        String userId = user.getUserId();
        String username = user.getUsername();
        String name = user.getName();
        System.out.println("Inserting now " + userId + " " + username + " " + name);
        try {
            jdbcTemplate.update(INSERT_USER_SQL, userId, username, name);
        } catch (Exception e) {
            return "Error";
        }
        return userId; //returns UUID
    }

    public String generateUUID(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString().substring(0,8);
        return randomUUIDString;
    }
    


}
