package it.epicode.U2_W7_D2.controller;


import it.epicode.U2_W7_D2.dto.UserDto;
import it.epicode.U2_W7_D2.exception.NonTrovatoException;
import it.epicode.U2_W7_D2.model.User;
import it.epicode.U2_W7_D2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
@Autowired
  private   UserService userService;


    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) throws NonTrovatoException {
        return userService.getUser(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody UserDto userDto) throws NonTrovatoException {
        return userService.updateUser(id,userDto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) throws NonTrovatoException {
       userService.deleteUser(id);
    }







}
