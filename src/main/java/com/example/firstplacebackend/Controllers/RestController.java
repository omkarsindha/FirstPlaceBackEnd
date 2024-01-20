package com.example.firstplacebackend.Controllers;
import com.example.firstplacebackend.Bean.User;
import com.example.firstplacebackend.Database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/users/")
public class RestController {

    @Autowired
    DatabaseAccess da;

    //retrieve the entire collection
    @GetMapping
    public List<User> getUsers() {
        return da.getUserCollection();
    }

    // this is GET - retrieve an individual entry from the collection
    @GetMapping("/{id}")
    public User getIndividualUser(@PathVariable int id) {
        return da.findUserById(id);
    }


    //checks user from database
    @PostMapping(path = "/login",consumes = "application/json")
    public Boolean loginUser(@RequestBody User user) {
        return da.findUserForLogin(user);
    }

    //posts user to database
    @PostMapping(consumes = "application/json")
    public Boolean registerUser(@RequestBody User user) {
        return da.registerUser(user);
    }



    // this is PUT - Replace an individual entry in the collection
    @PutMapping(path = "/{id}", consumes = "application/json")
    public String editUser(@PathVariable Long id, @RequestBody User user) {
        return da.editUser(id, user);
    }

    // this is DELETE - delete account
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        da.deleteUserAccount(id);
        return "Account Deleted Successfully";
    }



}
