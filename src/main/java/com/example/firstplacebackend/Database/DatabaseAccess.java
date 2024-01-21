package com.example.firstplacebackend.Database;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.example.firstplacebackend.Bean.Prompt;
import com.example.firstplacebackend.Bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class DatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public User findUserForLogin(User user) {
        MapSqlParameterSource np = new MapSqlParameterSource();
        String query = "SELECT * FROM users where email = :email and password = :password";
        np.addValue("email", user.getEmail());
        np.addValue("password", user.getPassword());
        return jdbc.query(query, np,new BeanPropertyRowMapper<User>(User.class)).get(0);
    }

    public Boolean registerUser(User user) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO users(username,password) VALUES(:username,:password)";
        namedParameters.addValue("username", user.getUsername());
        namedParameters.addValue("email", user.getEmail());
        namedParameters.addValue("password", user.getPassword());
        int count = jdbc.update(query, namedParameters);
        return count > 0;
    }

    public List<User> getUserCollection() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM users";
        List<User> users = jdbc.query(query, namedParameters, new BeanPropertyRowMapper<User>(User.class));
        for (User user:users){
            String query2 = "SELECT * FROM prompts WHERE userId = :id";
            namedParameters.addValue("id", user.getId());
            List<Prompt> prompts = jdbc.query(query2, namedParameters, new BeanPropertyRowMapper<Prompt>(Prompt.class));
            user.setPrompts(prompts);
        }
        return users;
    }

    public User findUserById(int id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM users WHERE id = :id";
        namedParameters.addValue("id",id);
        User user = jdbc.query(query, namedParameters, new BeanPropertyRowMapper<User>(User.class)).get(0);
        String query2 = "SELECT * FROM prompts WHERE userId = :userId";
        namedParameters.addValue("userId", user.getId());
        List<Prompt> prompts = jdbc.query(query2, namedParameters, new BeanPropertyRowMapper<Prompt>(Prompt.class));
        user.setPrompts(prompts);
        return user;
    }

    public String editUser(Long id, User user) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "UPDATE users SET " +
                "username=:username, " +
                "password=:password, " +
                "email=:email, " +
                "bio=:bio, " +
                "firstName=:firstName, " +
                "lastName=:lastName, " +
                "gender=:gender, " +
                "age=:age, " +
                "location=:location, " +
                "ethnicity=:ethnicity, " +
                "religion=:religion, " +
                "interests=:interests, " +
                "hobbies=:hobbies, " +
                "drinking=:drinking " +
                "WHERE id=:id";

        namedParameters.addValue("id", id);
        namedParameters.addValue("username", user.getUsername());
        namedParameters.addValue("password", user.getPassword());
        namedParameters.addValue("email", user.getEmail());
        namedParameters.addValue("bio", user.getBio());
        namedParameters.addValue("firstName", user.getFirstName());
        namedParameters.addValue("lastName", user.getLastName());
        namedParameters.addValue("gender", user.getGender());
        namedParameters.addValue("age", user.getAge());
        namedParameters.addValue("location", user.getLocation());
        namedParameters.addValue("ethnicity", user.getEthnicity());
        namedParameters.addValue("religion", user.getReligion());
        namedParameters.addValue("interests", user.getInterests());
        namedParameters.addValue("hobbies", user.getHobbies());
        namedParameters.addValue("drinking", user.getDrinking());

        jdbc.update(query, namedParameters);
        return "user info updated";
    }

    public void deleteUserAccount(Long id) {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("id", id);
            String query = "DELETE FROM users where id = :id";
            jdbc.update(query, namedParameters);
    }

//    public void insertUser() {
//        try {
//            String imagePath = "G:\\FirstPlaceBackEnd\\src\\main\\resources\\hi.png";
//            BufferedImage image = ImageIO.read(new File(imagePath));
//            byte[] imageBytes = convertImageToByteArray(image);
//
//            User user = new User();
//            user.setUsername("Omkar");
//            user.setPassword("Password");
//            user.setPfp(imageBytes);
//            System.out.println(Arrays.toString(user.getPfp()));
//            String insertQuery = "INSERT INTO users (name, password, pfp) VALUES (:name, :password, :pfp)";
//
//            MapSqlParameterSource parameters = new MapSqlParameterSource();
//            parameters.addValue("name", user.getUsername());
//            parameters.addValue("password", user.getPassword());
//            parameters.addValue("pfp", user.getPfp()); // Assuming pfp is a byte array
//
//            int rowsAffected = jdbc.update(insertQuery, parameters);
//
//            if (rowsAffected > 0) {
//                System.out.println("User inserted successfully!");
//            } else {
//                System.out.println("Failed to insert user.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

}