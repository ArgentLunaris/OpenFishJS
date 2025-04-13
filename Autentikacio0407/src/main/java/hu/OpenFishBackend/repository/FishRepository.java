package hu.OpenFishBackend.repository;

import hu.OpenFishBackend.model.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FishRepository extends JpaRepository<Fish, Integer> {


    @Query(value = "SELECT * FROM fish", nativeQuery = true)
    List<Fish> getAllFish();

    @Query(value = "SELECT * FROM fish WHERE id = :id", nativeQuery = true)
    Fish getFishById(@Param("id") int id);

    @Query(value = "SELECT * FROM fish WHERE distance BETWEEN my_distance-50 AND my_distance+50", nativeQuery = true)
    ArrayList<Fish> getFishByDistance(@Param("my_distance") int my_distance);

}
