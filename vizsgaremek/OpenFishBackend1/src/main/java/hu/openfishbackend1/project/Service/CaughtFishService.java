package hu.openfishbackend1.project.Service;

import hu.openfishbackend1.project.Model.CaughtFish.CaughtFish;
import hu.openfishbackend1.project.Model.Fish.Fish;
import hu.openfishbackend1.project.Model.Player.Player;
import hu.openfishbackend1.project.dto.CaughtFish.CaughtFishDto;
import hu.openfishbackend1.project.dto.CaughtFish.CreateCaughtFish;
import hu.openfishbackend1.repository.CaughtFishRepository;
//import hu.openfishbackend1.project.Converter.CaughtFishConverter;
import hu.openfishbackend1.repository.FishRepository;
import hu.openfishbackend1.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaughtFishService {

    @Autowired
    private CaughtFishRepository caughtFishRepository;
    private PlayerRepository playerRepository;
    private FishRepository fishRepository;

//        public CaughtFish createCaughtFish(CreateCaughtFish createCaughtFish) {
//            CaughtFish caughtFish = caughtFishRepository.insertIntoCaughtFish();
//
//        }
//        public Player createPlayer(CreatePlayer createPlayer){
//            Player player =playerRepository.save(PlayerConverter.convertCreateToModel(createPlayer));
//            return PlayerConverter.convertModelToRead(player);
//        }

        public List<CaughtFishDto> getAllCaughtFish() {
            List<Object[]> results = caughtFishRepository.findAllCaughtFishSimple();

            return results.stream()
                    .map(obj -> new CaughtFishDto(
                            ((Number) obj[0]).longValue(), // playerId
                            ((Number) obj[1]).longValue(), // fishId
                            (Integer) obj[2]              // amount
                    ))
                    .collect(Collectors.toList());
        }

    public CaughtFishService(CaughtFishRepository caughtFishRepository,
                             PlayerRepository playerRepository,
                             FishRepository fishRepository) {
        this.caughtFishRepository = caughtFishRepository;
        this.playerRepository = playerRepository;
        this.fishRepository = fishRepository;
    }

        public void createCaughtFish(CreateCaughtFish createCaughtFish) {

            if (!playerRepository.existsById(createCaughtFish.getPlayerId())) {
                throw new RuntimeException("Player not found");
            }

            // Validate that the Fish exists
            if (!fishRepository.existsById(createCaughtFish.getFishId())) {
                throw new RuntimeException("Fish not found");
            }

            // Use the native query to insert into the database
            caughtFishRepository.insertIntoCaughtFish(createCaughtFish.getPlayerId(), createCaughtFish.getFishId(), createCaughtFish.getAmount());

        }


    public void updateCaughtFishAmount(CreateCaughtFish request) {
        // Validate if player exists
        if (!playerRepository.existsById(request.getPlayerId())) {
            throw new RuntimeException("Player not found");
        }

        // Validate if fish exists
        if (!fishRepository.existsById(request.getFishId())) {
            throw new RuntimeException("Fish not found");
        }

        // Execute the native update query
        int updatedRows = caughtFishRepository.updateCaughtFishAmount(
                request.getPlayerId(),
                request.getFishId(),
                request.getAmount()
        );

        if (updatedRows == 0) {
            throw new RuntimeException("Caught fish record not found for this player and fish");
        }
    }


    public void deleteCaughtFishById(int id){
        int deletedRows = caughtFishRepository.deleteCaughtFishById(id);

        if (deletedRows == 0) {
            throw new RuntimeException("Caught fish record with ID " + id + " not found");
        }
    }


}
