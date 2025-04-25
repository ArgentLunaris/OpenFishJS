package hu.OpenFishBackend.service;

import hu.OpenFishBackend.converter.CaughtFishConverter;
import hu.OpenFishBackend.dto.caughtfish.CaughtFishDto;
import hu.OpenFishBackend.dto.caughtfish.CaughtFishUserId;
import hu.OpenFishBackend.model.CaughtFish;
import hu.OpenFishBackend.repository.CaughtFishRepository;
import hu.OpenFishBackend.repository.FishRepository;
import hu.OpenFishBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CaughtFishService {

    @Autowired
    private CaughtFishRepository caughtFishRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FishRepository fishRepository;

    @Autowired
    private CaughtFishConverter caughtFishConverter;



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


        int returnValue = 0;
        for(int i = 0; i< caughtFish.getAmount(); i++){
            returnValue += (randomFishWeight(caughtFish.getFishId())*100);
        }
        userRepository.updatePointsById(caughtFish.getUserId(), userRepository.getPointsById(caughtFish.getUserId())+returnValue);

    }

    public boolean playerCaughtFish(CaughtFishDto caughtFish) {
        if(caughtFishRepository.findAllByUserId(caughtFish.getUserId()) == 0) {
            System.out.println("jo");
            System.out.println("check return true");
            return true;
        }else{
            System.out.println("nem jo");
            if(caughtFishRepository.userCaughtFish(caughtFish.getUserId(), caughtFish.getFishId()) == 0) {
                System.out.println("else return true");
                return true;
            }else {
                System.out.println("else return false");
                return false;
            }
        }
    }

    public List<CaughtFishDto> allCaughtFishForId(CaughtFishUserId user){
        List<CaughtFish> newList = caughtFishRepository.findAllForAUser(user.getUserId());
        List<CaughtFishDto> dtoList = new ArrayList<>();
        for (CaughtFish c : newList) {
            dtoList.add(caughtFishConverter.caughtFishToDto(c));
        }

        return dtoList;
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
                caughtFishRepository.getCaughtFishByBothIds(request.getUserId(), request.getFishId()).getAmount()+request.getAmount()
        );

        int returnValue = 0;
        for(int i = 0; i< request.getAmount(); i++){
            returnValue += (randomFishWeight(request.getFishId())*100);
        }
        userRepository.updatePointsById(request.getUserId(), userRepository.getPointsById(request.getUserId()) + returnValue);



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

    public int randomFishWeight(int fish_id){

        int weight = 0;
        Random r = new Random();
        weight = (int) (r.nextInt((int) (fishRepository.getFishById(fish_id).getMaxWeight()-fishRepository.getFishById(fish_id).getMinWeight()))+fishRepository.getFishById(fish_id).getMinWeight());
        System.out.println("the weight is "+weight);
        return weight;
    }

}
