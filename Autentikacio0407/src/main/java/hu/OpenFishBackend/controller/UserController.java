package hu.OpenFishBackend.controller;

import hu.OpenFishBackend.dto.users.*;
import hu.OpenFishBackend.model.Users;
import hu.OpenFishBackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //autentikációhoz kellő dolgok

    @PostMapping("/register")
    public Users register(@RequestBody @Valid UserRegister user) {

        if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "szia, nem lehet üres a felhasználónév sem, a jelszó sem és az email sem!");
        }else if(!Objects.equals(userService.userExistsByUsernameOrEmail(user.getUsername(), user.getEmail()), "No user like this exists")){
            System.out.println("idk");
        }else if(!Pattern.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", user.getEmail())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "nem jó az email regex! ☺");
        }

        return userService.register(user);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserLogin user) {
        //authentication
        //authenticatedPassword
        UserResponse response = new UserResponse();
        response.setToken(userService.verify(user));
        response.setUserId(userService.getUserId(user.getUsername(), user.getPassword()));
        return response;
    }

    @PostMapping("/checkToken")
    public boolean checkToken(@RequestHeader(name = "Authorization") String token,@RequestBody Integer userId) {
        return userService.checkToken(token.substring(7), userId);
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
        userService.updateUser(id, updateUser);
        return "User was updated with these parameters: "+id+"\n"+ updateUser.getUsername()+"\n"+ updateUser.getEmail()+"\n"+ updateUser.getPassword();
    }

    @DeleteMapping("/{id}")
    public Users deletePlayer(@PathVariable int id){
        return userService.deletePlayer(id);
    }

    @PostMapping("/getPointsById")
    public int getUserPoints(@RequestBody UserId id){
        return userService.getUserPoints(id.getId());
    }


}
