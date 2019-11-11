package com.ditraacademy.travelagency.core;

import com.ditraacademy.travelagency.utils.AcceptedResponseModel;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    UserRepository userRepository ;
    @PostMapping ("/user")
    public ResponseEntity<?> creatUser(@RequestBody User user){
        if ( user.getName()==null) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(" user name was not sent");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        if ( user.getName().length()<3) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(" user name is not valid ");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        if ( user.getAge()==0) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(" user age was not send ");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        if ( user.getAge()<0) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(" user age is not valide ");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        user = userRepository.save(user);
        return  new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/users")
    public List<User> GetUsers (){
        return userRepository.findAll();
    }
    @GetMapping("/user/{id}")
    public Optional<User> getUserbyId(@PathVariable int id ){

        return userRepository.findById(id);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> DeleteUser ( @PathVariable int id ){
       Optional<User> userOp  =     userRepository.findById(id);
       if ( userOp.isPresent()){
           userRepository.deleteById(id);
           return new ResponseEntity<>(userOp.get(),HttpStatus.OK);
       }
       else  {
           return   new ResponseEntity<>(new ErrorResponseModel("the user that you want to delete " +
                   "does not exist "),HttpStatus.BAD_REQUEST) ;
       }

    }
    @PutMapping ("/user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable int id ){
        System.out.println(user);
        Optional<User> userOptional = userRepository.findById(id);
        if ( userOptional.isPresent()){

            User updatedUser = userOptional.get();
            if ( user.getName()!=null) {
                if ( user.getName().length()<3)
                    return new   ResponseEntity<>( new ErrorResponseModel("invalid name"),HttpStatus.BAD_REQUEST);
                else
                    updatedUser.setName(user.getName());

            }
            if ( user.getAge()!=0) {
                if ( user.getAge()<0)
                    return new   ResponseEntity<>( new ErrorResponseModel("invalid age"),HttpStatus.BAD_REQUEST);
                else
                    updatedUser.setAge(user.getAge());

            }
            userRepository.save(updatedUser);
            return new ResponseEntity<>(updatedUser , HttpStatus.OK);

        }
        else {
            return   new ResponseEntity<>(new ErrorResponseModel("the user that you want to updated " +
                    "does not exist "),HttpStatus.BAD_REQUEST) ;
        }

}}
