USE assessment;
INSERT INTO user(user_id, username, name) VALUES 
('1b80114c', 'fred', 'Fred'),
('cf66dae3', 'wilma', 'Wilma'),
('a8b9800d', 'barney', 'Barney'),
('66223e28', 'betty', 'Betty');

SELECT * FROM user;

SELECT * FROM user WHERE username = 'joel';

INSERT INTO user(user_id, username, name) VALUES ('12a45b78', 'joel', 'Joel');
