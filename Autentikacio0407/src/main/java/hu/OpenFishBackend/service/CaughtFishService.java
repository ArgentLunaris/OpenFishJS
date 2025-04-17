package hu.OpenFishBackend.service;

import hu.OpenFishBackend.dto.caughtfish.CaughtFishDto;
import hu.OpenFishBackend.dto.caughtfish.CaughtFishUserId;
import hu.OpenFishBackend.repository.CaughtFishRepository;
import hu.OpenFishBackend.repository.FishRepository;
import hu.OpenFishBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaughtFishService {

    @Autowired
    private CaughtFishRepository caughtFishRepository;
    private UserRepository userRepository;
    private FishRepository fishRepository;


    public List<CaughtFishDto> getAllCaughtFish() {
        List<Object[]> results = caughtFishRepository.findAllCaughtFishSimple();
        return results.stream()
                .map(obj -> new CaughtFishDto(
                        ((Number) obj[0]).intValue(), //
                        ((Number) obj[1]).intValue(), // fishId
                        (Integer) obj[2]              // amount
                ))
                .collect(Collectors.toList());
    }

    public CaughtFishService(CaughtFishRepository caughtFishRepository,
                             UserRepository userRepository,
                             FishRepository fishRepository) {
        this.caughtFishRepository = caughtFishRepository;
        this.userRepository = userRepository;
        this.fishRepository = fishRepository;
    }

    public void createCaughtFish(CaughtFishDto caughtFish) {

        if (userRepository.getUsersById(caughtFish.getUserId()) == null) {
            throw new RuntimeException("User not found");
        }

        // Validate that the Fish exists
        if (fishRepository.getFishById(caughtFish.getFishId()) == null) {
            throw new RuntimeException("Fish not found");
        }
//        System.out.println("Service: "+ caughtFish.toString());

        // Use the native query to insert into the database
        caughtFishRepository.insertIntoCaughtFish(caughtFish.getUserId(), caughtFish.getFishId(), caughtFish.getAmount());

    }

    public boolean asd(CaughtFishDto caughtFish) {
        if(caughtFishRepository.findAllByUserId(caughtFish.getFishId()) == 1) {
            return false;
        }else{
            return true;
        }
    }

    public List<CaughtFishDto> allCaughtFishForId(CaughtFishUserId user){
        return caughtFishRepository.findAllForAUser(user.getUserId());
    }



    public void updateCaughtFishAmount(CaughtFishDto request) {
        // Validate if user exists
        if (!userRepository.existsById(request.getUserId())) {
            throw new RuntimeException("User not found");
        }

        // Validate if fish exists
        if (!fishRepository.existsById(request.getFishId())) {
            throw new RuntimeException("Fish not found");
        }

        // Execute the native update query
        int updatedRows = caughtFishRepository.updateCaughtFishAmount(
                request.getUserId(),
                request.getFishId(),
                request.getAmount()
        );

        if (updatedRows == 0) {
            throw new RuntimeException("Caught fish record not found for this user and fish");
        }
    }


    public void deleteCaughtFishById(int id){
        int deletedRows = caughtFishRepository.deleteCaughtFishById(id);

        if (deletedRows == 0) {
            throw new RuntimeException("Caught fish record with ID " + id + " not found");
        }
    }
}
