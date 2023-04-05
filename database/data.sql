USE assessment;
INSERT INTO user(user_id, username, name) VALUES 
('1b80114c', 'fred', 'Fred'),
('cf66dae3', 'wilma', 'Wilma'),
('a8b9800d', 'barney', 'Barney'),
('66223e28', 'betty', 'Betty');

SELECT * FROM user;
SELECT * FROM task;

SELECT * FROM user WHERE username = 'joel';

INSERT INTO user(user_id, username, name) VALUES ('12a45b78', 'joel', 'Joel');

DELETE FROM task WHERE task_id > 0;
ALTER TABLE task AUTO_INCREMENT = 1;




-- testing insert
INSERT INTO task (user_id, description, priority, due_date)
VALUES 
    ('Finish project', 2, '2023-04-30'),
    ('Buy groceries', 3, '2023-04-08'),
    ('Pay bills', 1, '2023-04-15');
    
SELECT * FROM task;

