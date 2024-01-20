CREATE TABLE IF NOT EXISTS users (
                                     id LONG PRIMARY KEY AUTO_INCREMENT,
                                     name VARCHAR(255),
                                     password VARCHAR(255),
                                     pfp LONGBLOB
);

CREATE TABLE IF NOT EXISTS prompts (
                                     userId INT,
                                     photo LONGBLOB,
                                     prompt varchar(255)
);

CREATE TABLE IF NOT EXISTS messages (
                                       senderId INT,
                                       receiverID INT,
                                       message varchar(255)
);

INSERT INTO users (name,password,pfp) VALUES ('User1','Pass1','null');
INSERT INTO users (name,password,pfp) VALUES ('User2','Pass2','null');
INSERT INTO prompts (userId,photo,prompt) VALUES ('1','null','Hi this is prompt');
INSERT INTO messages (senderId,receiverID,message) VALUES ('1','2','Hi this is msg');
INSERT INTO messages (senderId,receiverID,message) VALUES ('2','1','Hi this is reply');