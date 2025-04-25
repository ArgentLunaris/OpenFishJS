package hu.OpenFishBackend.service;

import hu.OpenFishBackend.Exceptions.EmailAlreadyExists;
import hu.OpenFishBackend.Exceptions.UserNotFoundException;
import hu.OpenFishBackend.Exceptions.UsernameAlreadyExists;
import hu.OpenFishBackend.converter.UserConverter;
import hu.OpenFishBackend.dto.users.UpdateUsers;
import hu.OpenFishBackend.dto.users.UserLogin;
import hu.OpenFishBackend.dto.users.UserRegister;
import hu.OpenFishBackend.model.UserPrincipal;
import hu.OpenFishBackend.model.Users;
import hu.OpenFishBackend.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users register(UserRegister user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(UserConverter.convertRegisterToModel(user));
    }

    public String verify(UserLogin user) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));




        if(bCryptPasswordEncoder.matches(user.getPassword(), userRepository.findByUsername(user.getUsername()).getPassword())){
            return jwtService.generateToken(user.getUsername());
        }else{
            return "Fail";
        }

    }


    //


    public List<Users>  listAll(){return userRepository.listUsers();}


    public Users readUser(int id) {

            if(!userRepository.existsById(id)){
                throw new UserNotFoundException();
            }
            return userRepository.getUsersById(id);

    }

    public void updateUser(int id, UpdateUsers updateUsers) {
        if(!userRepository.existsById(id)){
        throw new RuntimeException("Player not found");
        }
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        updateUsers.setPassword(bCryptPasswordEncoder.encode(updateUsers.getPassword()));


        userRepository.updateUser(id, updateUsers.getUsername(), updateUsers.getEmail(), updateUsers.getPassword());
//        Users newUser = userRepository.save(UserConverter.convertUpdateToModel(id, updateUsers));
//        return userRepository.updateUser(id, newUsername, newEmail, newPassword);

    }

    public Users deletePlayer(int id) {

        Users deletingPlayer = readUser(id);
        userRepository.deleteUserById(id);
        return deletingPlayer;
    }

    public String userExistsByUsernameOrEmail(String username, String email) {
        if(userRepository.getUsersByUsername(username) == 1){
            throw new UsernameAlreadyExists("User with this username already exists"); //ResponseStatusException(HttpStatus.FOUND, );
        }else if(userRepository.getUsersByEmail(email) == 1){
            throw new EmailAlreadyExists("User with this email already exists");
        }
        return "No user like this exists";
    }

    public int getUserId(String username, String password){
        return userRepository.findByUsername(username).getId();
    }


    public boolean checkToken(String token, Integer userId) {

        try{
            return jwtService.validateToken(token, new UserPrincipal(userRepository.getReferenceById(userId)));
        }catch (Exception e){
            return false;
        }

    }

    public int getUserPoints(int id) {
        return userRepository.getPointsOfUser(id);
    }
}
