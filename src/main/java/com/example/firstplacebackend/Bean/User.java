package com.example.firstplacebackend.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    int id;
    String username;
    String password;
    String email;
    String firstName;
    String lastName;
    String bio;
    String gender;
    Integer age;
    String location;
    String ethnicity;
    String religion;
    String interests;
    String hobbies;
    String drinking;
    List<Prompt> prompts = new ArrayList<Prompt>();
}
