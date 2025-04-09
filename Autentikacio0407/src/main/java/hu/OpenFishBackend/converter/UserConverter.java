package hu.OpenFishBackend.converter;

import hu.OpenFishBackend.dto.users.UpdateUsers;
import hu.OpenFishBackend.model.Users;

public class UserConverter {
    public static Users convertModelToRead(Users user) {
        Users playerUser = new Users();
        playerUser.setId(user.getId());
        playerUser.setUsername(user.getUsername());
        playerUser.setEmail(user.getEmail());
        playerUser.setPassword(user.getPassword());
        playerUser.setPoints(user.getPoints());
        playerUser.setRole(user.getRole());
        return playerUser;

    }

    public static Users convertUpdateToModel(Integer id, UpdateUsers updateUsers) {
        Users user = new Users();
        user.setId(id);
        user.setUsername(updateUsers.getUsername());
        user.setEmail(updateUsers.getEmail());
        user.setPassword(updateUsers.getPassword());
        return user;
    }




}
