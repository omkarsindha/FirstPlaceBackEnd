package com.example.firstplacebackend.Database;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.example.firstplacebackend.Bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.example.firstplacebackend.Utility.HelperMethods;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

import static com.example.firstplacebackend.Utility.HelperMethods.convertImageToByteArray;

@Repository
public class DatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public Boolean findUserForLogin(User user) {
        user.setName("User1");
        user.setPassword("Pass1");
        MapSqlParameterSource np = new MapSqlParameterSource();
        String query = "SELECT * FROM users where name = :name and password = :password";
        np.addValue("name", user.getName());
        np.addValue("password", user.getPassword());
        int count = jdbc.query(query, np,new BeanPropertyRowMapper<User>(User.class)).size();
        return count>0;
    }

    public Boolean registerUser(User user) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO users(name,password) VALUES(:name,:password)";
        namedParameters.addValue("name", user.getName());
        namedParameters.addValue("password", user.getPassword());
        int count = jdbc.update(query, namedParameters);
        return count > 0;
    }

    public List<User> getUserCollection() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM users";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<User>(User.class));
    }

    public List<User> findUserById(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM users WHERE id = :id";
        namedParameters.addValue("id", id);
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<User>(User.class));
    }

    public String editUser(Long id, User user) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "UPDATE users SET name=:name, password=:password, pfp=:pfp where id=:id";
        namedParameters.addValue("id", id);
        namedParameters.addValue("name", user.getName());
        namedParameters.addValue("password", user.getPassword());
        namedParameters.addValue("pfp", user.getPfp());
        namedParameters.addValue("prompts", user.getPrompts());
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