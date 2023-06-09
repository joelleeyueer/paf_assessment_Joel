CREATE SCHEMA IF NOT EXISTS assessment;

USE assessment;


CREATE TABLE IF NOT EXISTS user (
  user_id VARCHAR(8) NOT NULL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS task (
  task_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(8) NOT NULL,
  description VARCHAR(255) NOT NULL,
  priority INT NOT NULL CHECK (priority BETWEEN 1 AND 3),
  due_date DATE NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);






