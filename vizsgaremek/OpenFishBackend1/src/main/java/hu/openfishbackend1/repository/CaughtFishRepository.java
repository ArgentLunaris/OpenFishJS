package hu.openfishbackend1.repository;

import hu.openfishbackend1.project.Model.CaughtFish.CaughtFish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CaughtFishRepository extends JpaRepository<CaughtFish, Integer> {

    @Query(value = "SELECT cf.playerId, cf.fishId, cf.amount FROM caughtfish cf", nativeQuery = true)
    List<Object[]> findAllCaughtFishSimple();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO caughtfish (playerId, fishId, amount) VALUES (:playerId, :fishId, :amount)", nativeQuery = true)
    void insertIntoCaughtFish(@Param("playerId") int playerId, @Param("fishId") int fishId, @Param("amount") int amount);


    @Modifying
    @Transactional
    @Query(value = "UPDATE caughtfish SET amount = :amount WHERE playerId = :playerId AND fishId = :fishId", nativeQuery = true)
    int updateCaughtFishAmount(@Param("playerId") int playerId, @Param("fishId") int fishId, @Param("amount") int amount);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM caughtfish WHERE id = :id", nativeQuery = true)
    int deleteCaughtFishById(@Param("id") int id);

}
