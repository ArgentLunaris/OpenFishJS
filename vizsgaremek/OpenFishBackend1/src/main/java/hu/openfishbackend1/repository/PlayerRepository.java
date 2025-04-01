package hu.openfishbackend1.repository;

import hu.openfishbackend1.project.Model.Player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
