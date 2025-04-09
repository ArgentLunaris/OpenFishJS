package hu.OpenFishBackend.service;

import hu.OpenFishBackend.Exceptions.FishNotFoundException;
import hu.OpenFishBackend.model.Fish;
import hu.OpenFishBackend.repository.FishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishService {

    @Autowired
    private FishRepository fishRepository;

    public List<Fish> listAll() {return fishRepository.getAllFish();}

    public Fish getFishById(Integer id) {
        if (!fishRepository.existsById(id)) {
            throw new FishNotFoundException();}

        return fishRepository.getFishById(id);
    }

//    public RandomFish getFishByDistance(){
//        ArrayList<Fish> fishList = new ArrayList<>(fishRepository.getFishByDistance(distance));
//        int randomNumber = Math.random()* fishList.size();
//        return
//
//    }

}
