package hu.OpenFishBackend.controller;

import hu.OpenFishBackend.dto.users.UpdateUsers;
import hu.OpenFishBackend.dto.users.UserLogin;
import hu.OpenFishBackend.dto.users.UserRegister;
import hu.OpenFishBackend.model.Users;
import hu.OpenFishBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //autentikációhoz kellő dolgok

    @PostMapping("/register")
    public Users register(@RequestBody UserRegister user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLogin user) {
        //authentication
        //authenticatedPassword
        return userService.verify(user);
    }


    //többi végpont
    @GetMapping("/listPlayers")
    public List<Users> getPlayers(){
        return userService.listAll();
    }


    @GetMapping("/{id}")
    public Users getUserById(@PathVariable int id){
        return userService.readUser(id);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody UpdateUsers updateUser){
        System.out.println("controller: "+updateUser);
        userService.updateUser(id, updateUser);
        return "User was updated with these parameters: "+id+"\n"+ updateUser.getUsername()+"\n"+ updateUser.getEmail()+"\n"+ updateUser.getPassword();
    }

    @DeleteMapping("/{id}")
    public Users deletePlayer(@PathVariable int id){
        return userService.deletePlayer(id);
    }



}
