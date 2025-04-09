package hu.OpenFishBackend.repository;

import hu.OpenFishBackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<Users> listUsers();

    @Query(value = "SELECT * FROM users WHERE id = :id",nativeQuery = true)
    Users getUsersById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET username = :username, email = :email, password = :password WHERE id = :id", nativeQuery = true)
    void updateUser(@Param("id") int id, @Param("username") String username, @Param("email") String email, @Param("password") String password);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE id = :id",nativeQuery = true)
    void deleteUserById(@Param("id") int id);
}
