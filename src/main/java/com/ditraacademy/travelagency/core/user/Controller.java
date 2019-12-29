package com.ditraacademy.travelagency.core.user;

import com.ditraacademy.travelagency.core.user.model.SignInModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    UserServices userService;

    @PostMapping("/user")
    public ResponseEntity<?> creatUser(@RequestBody User user) {

        return userService.creatUser(user);
    }

    @GetMapping("/users")
    public List<User> GetUsers() {
        return userService.GetUsers();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserbyId(@PathVariable int id) {

        return userService.getUserbyId(id);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable int id) {
       return userService.DeleteUser(id);

    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable int id) {
        return userService.updateUser(user,id);
    }


    @PostMapping("/auth/signin")
    public  ResponseEntity<? > signIn(@RequestBody  SignInModel signInModel){
        return  userService.signIn(signInModel);
    }
}