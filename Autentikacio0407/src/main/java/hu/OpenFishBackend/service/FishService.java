package hu.OpenFishBackend.service;

import hu.OpenFishBackend.Exceptions.FishNotFoundException;
import hu.OpenFishBackend.converter.FishConverter;
import hu.OpenFishBackend.dto.fish.FishDistance;
import hu.OpenFishBackend.dto.fish.RandomFish;
import hu.OpenFishBackend.model.Fish;
import hu.OpenFishBackend.repository.FishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FishService {

    @Autowired
    private FishRepository fishRepository;
    @Autowired
    private FishConverter fishConverter;

    public List<Fish> listAll() {return fishRepository.getAllFish();}

    public Fish getFishById(Integer id) {
        if (!fishRepository.existsById(id)) {
            throw new FishNotFoundException();}

        return fishRepository.getFishById(id);
    }

    public RandomFish getFishByDistance(FishDistance fishDistance){
        System.out.println((fishDistance.getDistance()+51)+" is the distance");
        ArrayList<Fish> fishList = new ArrayList<>(fishRepository.getFishByDistance(fishDistance.getDistance()-50, fishDistance.getDistance()+50));
        int randomNumber = (int)(Math.random()* fishList.size());
        for (Fish f : fishList) {
            System.out.println(f+" fish :)");
        }
        if(!fishList.isEmpty()) {
            return fishConverter.convertModelToRandom(fishList.get(randomNumber));
        }else{
            System.out.println("szar");
            return null;
        }
    }
    //10m

}
