package com.example.firstplacebackend.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    String name;
    String password;
    byte[] pfp;
    ArrayList<Prompt> prompts = new ArrayList<Prompt>();
}
