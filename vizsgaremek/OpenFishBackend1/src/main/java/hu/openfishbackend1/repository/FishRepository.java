package hu.openfishbackend1.repository;

import hu.openfishbackend1.project.Model.Fish.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends JpaRepository<Fish, Integer> {
}
