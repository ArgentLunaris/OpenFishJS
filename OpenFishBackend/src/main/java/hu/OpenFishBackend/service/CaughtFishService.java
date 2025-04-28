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

    private final int SCORE_MULT = 10;

    public List<CaughtFishDto> getAllCaughtFish() {
        List<CaughtFish> results = caughtFishRepository.findAllCaughtFishSimple();
        return results.stream()
                .map(obj -> new CaughtFishDto(
                        obj.getUserId().getId(),
                        obj.getFishId().getId(),
                        obj.getAmount(),
                        obj.getRecord()
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

    public CaughtFishDto createCaughtFish(UpdateCaughtfish caughtFish) {

        if (userRepository.getUsersById(caughtFish.getUserId()) == null) {
            throw new RuntimeException("User not found");
        }


        if (fishRepository.getFishById(caughtFish.getFishId()) == null) {
            throw new RuntimeException("Fish not found");
        }
//        System.out.println("Service: "+ caughtFish.toString());

        caughtFishRepository.insertIntoCaughtFish(caughtFish.getUserId(), caughtFish.getFishId(), 1, caughtFish.getWeight());

        userRepository.updatePointsById(caughtFish.getUserId(), (int) (userRepository.getPointsById(caughtFish.getUserId()) + caughtFish.getWeight()*SCORE_MULT));

        return new CaughtFishDto(caughtFish.getUserId(), caughtFish.getFishId(), 1, caughtFish.getWeight());
    }

    public boolean playerCaughtFish(UpdateCaughtfish caughtFish) {
        return caughtFishRepository.userCaughtFish(caughtFish.getUserId(), caughtFish.getFishId()) == 0;
    }

    public List<CaughtFishDto> allCaughtFishForId(CaughtFishUserId user){
        List<CaughtFish> newList = caughtFishRepository.findAllForAUser(user.getUserId());
        List<CaughtFishDto> dtoList = new ArrayList<>();
        for (CaughtFish c : newList) {
            dtoList.add(caughtFishConverter.caughtFishToDto(c));
        }

        return dtoList;
    }



    public CaughtFishDto updateCaughtFishAmount(UpdateCaughtfish request) {
        if (!userRepository.existsById(request.getUserId())) {
            throw new RuntimeException("User not found");
        }

        if (!fishRepository.existsById(request.getFishId())) {
            throw new RuntimeException("Fish not found");
        }

        int newAmount = caughtFishRepository.getCaughtFishByBothIds(request.getUserId(), request.getFishId()).getAmount()+1;

        caughtFishRepository.updateCaughtFishAmount(
                request.getUserId(),
                request.getFishId(),
                newAmount
        );

        userRepository.updatePointsById(request.getUserId(), (int) (userRepository.getPointsById(request.getUserId()) + request.getWeight()*SCORE_MULT));

        return new CaughtFishDto(request.getUserId(), request.getFishId(), newAmount, 0);
    }


    public void deleteCaughtFishById(int id){
        int deletedRows = caughtFishRepository.deleteCaughtFishById(id);

        if (deletedRows == 0) {
            throw new RuntimeException("Caught fish record with ID " + id + " not found");
        }
    }

    public float checkForRecord(float weight, int userId, int fishId){
        float currentRecord = caughtFishRepository.getRecord(userId, fishId);
        if(currentRecord < weight){
            caughtFishRepository.updateRecord(weight, userId, fishId);
            return weight;
        }
        return currentRecord;
    }


    public int randomFishWeight(int fish_id){

        int weight = 0;
        Random r = new Random();
        weight = (int) (r.nextInt((int) (fishRepository.getFishById(fish_id).getMaxWeight()-fishRepository.getFishById(fish_id).getMinWeight()))+fishRepository.getFishById(fish_id).getMinWeight());
        System.out.println("the weight is "+weight);
        return weight;
    }

}
