package hu.OpenFishBackend.service;

import hu.OpenFishBackend.converter.CaughtFishConverter;
import hu.OpenFishBackend.dto.caughtfish.CaughtFishDto;
import hu.OpenFishBackend.dto.caughtfish.CaughtFishUserId;
import hu.OpenFishBackend.dto.caughtfish.UpdateCaughtfish;
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

    public void createCaughtFish(UpdateCaughtfish caughtFish) {

        if (userRepository.getUsersById(caughtFish.getUserId()) == null) {
            throw new RuntimeException("User not found");
        }

        // Validate that the Fish exists
        if (fishRepository.getFishById(caughtFish.getFishId()) == null) {
            throw new RuntimeException("Fish not found");
        }
//        System.out.println("Service: "+ caughtFish.toString());

        // Use the native query to insert into the database
        caughtFishRepository.insertIntoCaughtFish(caughtFish.getUserId(), caughtFish.getFishId(), caughtFishRepository.getCaughtFishByBothIds(caughtFish.getUserId(), caughtFish.getFishId()).getAmount()+1);

        userRepository.updatePointsById(caughtFish.getUserId(), (int) (caughtFish.getWeight()*100));

    }

    public boolean playerCaughtFish(UpdateCaughtfish caughtFish) {
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



    public void updateCaughtFishAmount(UpdateCaughtfish request) {
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
                caughtFishRepository.getCaughtFishByBothIds(request.getUserId(), request.getFishId()).getAmount()+1
        );

        userRepository.updatePointsById(request.getUserId(), (int) (userRepository.getPointsById(request.getUserId()) + request.getWeight()*100));



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

    public String checkForRecord(float weight, int userId, int fishId){
        if(caughtFishRepository.checkForRecord(userId, fishId) < weight){
            caughtFishRepository.updateRecord(weight, userId, fishId);
            return "New record for user "+userId+" for fish "+fishId+" with the weight of "+ weight;
        }else{
            return "No new record";
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
