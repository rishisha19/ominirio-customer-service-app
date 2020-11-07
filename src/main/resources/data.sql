DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role;

CREATE TABLE role (
  roleId VARCHAR(250) PRIMARY KEY,
  roleName VARCHAR(250) UNIQUE NOT NULL,
  roleCode VARCHAR(250) UNIQUE NOT NULL
);

CREATE TABLE users (
  userId VARCHAR(250)  PRIMARY KEY,
  userName VARCHAR(250) NOT NULL,
  dateOfBirth Date,
  gender CHAR(20) NOT NULL,
  phoneNumber VARCHAR(250) DEFAULT NULL

);



CREATE TABLE user_role (
  userId VARCHAR(250) UNIQUE NOT NULL,
  password VARCHAR(250),
  roleCode VARCHAR(250) NOT NULL,
  FOREIGN KEY (userId) REFERENCES users(userId)
);

INSERT INTO role (roleId, roleName, roleCode) VALUES
  ('id1001', 'Customer', 'Customer'),
  ('id1002', 'Branch Manager', 'Branch Manager');

INSERT INTO users (userId, userName, gender, phoneNumber) VALUES
  ('usr_01', 'admin', 'Female','9000888'),
  ('usr_02', 'test', 'Male','');

INSERT INTO user_role (userId, password, roleCode) VALUES
('usr_01', 'pwd', 'Branch Manager'),
('usr_02', '', 'Customer');

