package hu.OpenFishBackend.repository;

import hu.OpenFishBackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<Users> listUsers();

    @Query(value = "SELECT * FROM users WHERE id = :id",nativeQuery = true)
    Users getUsersById(@Param("id") int id);

    @Query(value = "SELECT EXISTS(SELECT TRUE FROM users WHERE username = :username)", nativeQuery = true)
    Long getUsersByUsername(@Param("username") String username);

    @Query(value = "SELECT EXISTS(SELECT TRUE FROM users WHERE email = :email)", nativeQuery = true)
    Long getUsersByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET username = :username, email = :email, password = :password WHERE id = :id", nativeQuery = true)
    void updateUser(@Param("id") int id, @Param("username") String username, @Param("email") String email, @Param("password") String password);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE id = :id",nativeQuery = true)
    void deleteUserById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET points = :points WHERE id = :id", nativeQuery = true)
    void updatePointsById(@Param("id") int id, @Param("points") int points);

    @Query(value = "SELECT points FROM users WHERE id = :id ", nativeQuery = true)
    int getPointsById(@Param("id") int id);

    @Query(value = "SELECT points FROM users WHERE id = :id", nativeQuery = true)
    int getPointsOfUser(@Param("id") int id);
}
