package hu.OpenFishBackend.repository;

import hu.OpenFishBackend.dto.caughtfish.CaughtFishDto;
import hu.OpenFishBackend.model.CaughtFish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CaughtFishRepository extends JpaRepository<CaughtFish, Integer> {


    @Query(value = "SELECT * FROM caughtfish cf", nativeQuery = true)
    List<CaughtFish> findAllCaughtFishSimple();





    @Query(value = "SELECT EXISTS(SELECT * FROM caughtfish WHERE user_id = :user_id)", nativeQuery = true)
    Long findAllByUserId(@Param("user_id") Integer userId);

    @Query(value = "SELECT * FROM caughtfish WHERE user_id = :user_id", nativeQuery = true)
    List<CaughtFish> findAllForAUser(@Param("user_id") int userId);



    @Query(value = "SELECT * FROM caughtfish WHERE fish_id = :fish_id", nativeQuery = true)
    List<CaughtFishDto> findAllByFishId(@Param("fish_id") Integer fishId);






    @Modifying
    @Transactional
    @Query(value = "INSERT INTO caughtfish (user_id, fish_id, amount, record) VALUES (:user_id, :fish_id, :amount, :record)", nativeQuery = true)
    void insertIntoCaughtFish(@Param("user_id") int userId, @Param("fish_id") int fishId, @Param("amount") int amount, @Param("record") float record);


    @Modifying
    @Transactional
    @Query(value = "UPDATE caughtfish SET amount = :amount WHERE user_id = :user_id AND fish_id = :fish_id", nativeQuery = true)
    int updateCaughtFishAmount(@Param("user_id") int userId, @Param("fish_id") int fishId, @Param("amount") int amount);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM caughtfish WHERE id = :id", nativeQuery = true)
    int deleteCaughtFishById(@Param("id") int id);

    @Query(value = "SELECT EXISTS(SELECT * FROM caughtfish WHERE user_id = :user_id AND fish_id = :fish_id)", nativeQuery = true)
    int userCaughtFish(@Param("user_id") int userId, @Param("fish_id") int fish_id);

    @Query(value = "SELECT * FROM caughtfish WHERE user_id = :user_id AND fish_id = :fish_id", nativeQuery = true)
    CaughtFish getCaughtFishByBothIds(@Param("user_id") int userId, @Param("fish_id") int fishId);

    @Query(value = "SELECT record FROM caughtfish WHERE user_id = :user_id AND fish_id = :fish_id", nativeQuery = true)
    float getRecord(@Param("user_id") int userId, @Param("fish_id") int fishId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE caughtfish SET record = :weight WHERE user_id = :user_id AND fish_id = :fish_id", nativeQuery = true)
    void updateRecord(@Param("weight") float weight, @Param("user_id") int userId, @Param("fish_id") int fishId);
}
