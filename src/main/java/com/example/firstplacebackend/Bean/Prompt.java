package com.example.firstplacebackend.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prompt {
   int id;
   int userID;
   byte[] image;
   String caption;
}
