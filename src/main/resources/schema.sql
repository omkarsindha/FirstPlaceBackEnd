CREATE TABLE IF NOT EXISTS users (
                                     id LONG PRIMARY KEY AUTO_INCREMENT,
                                     username VARCHAR(255),
                                     password VARCHAR(255),
                                     pfp LONGBLOB,
                                     firstName VARCHAR(50),
                                     lastName VARCHAR(50),
                                     bio VARCHAR(50),
                                     gender VARCHAR(50),
                                     age INT,
                                     location VARCHAR(50),
                                     ethnicity VARCHAR(50),
                                     religion VARCHAR(50),
                                     interests VARCHAR(255),
                                     hobbies VARCHAR(255),
                                     drinking VARCHAR(50)

);

CREATE TABLE IF NOT EXISTS prompts (
                                     id LONG PRIMARY KEY AUTO_INCREMENT,
                                     userId INT,
                                     image LONGBLOB,
                                     caption varchar(255)
);



INSERT INTO users (username,password,pfp,firstname,lastname,bio,gender,age,location,ethnicity,religion,interests,hobbies,drinking)
            VALUES ('User1','Pass1','null','fn','ln','bio','gender',19,'Mississauga','Asian','Hindu','int','hob','drinking');
INSERT INTO users (username,password,pfp,firstname,lastname,bio,gender,age,location,ethnicity,religion,interests,hobbies,drinking)
            VALUES ('User2','Pass2','null2','fn2','ln2','bio2','gender2',18,'Mississauga','Asian2','Hindu2','int2','hob2','drinking2');
INSERT INTO prompts (userId,image,caption) VALUES ('1','null1','Hi this is prompt1');
INSERT INTO prompts (userId,image,caption) VALUES ('1','null2','Hi this is prompt2');

CREATE TABLE IF NOT EXISTS messages (
                                        senderId INT,
                                        receiverID INT,
                                        message varchar(255)
);
INSERT INTO messages (senderId,receiverID,message) VALUES ('1','2','Hi this is msg');
INSERT INTO messages (senderId,receiverID,message) VALUES ('2','1','Hi this is reply');