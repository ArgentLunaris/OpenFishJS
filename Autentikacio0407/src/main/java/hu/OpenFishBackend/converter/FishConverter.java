package hu.OpenFishBackend.converter;

import hu.OpenFishBackend.dto.fish.RandomFish;
import hu.OpenFishBackend.model.Fish;
import org.springframework.stereotype.Component;

@Component
public class FishConverter {

    public RandomFish convertModelToRandom(Fish fish){
        RandomFish randomFish = new RandomFish();
        randomFish.setId(fish.getId());
        randomFish.setSpecies(fish.getSpecies());
        randomFish.setRarity(fish.getRarity());
        float min = fish.getMinWeight();
        float max = fish.getMaxWeight();
        float weight = (float) ((Math.random()*(max-min))+min);
        boolean temp = true;
        while(temp) {
            if(weight < max && weight > min){

                randomFish.setWeight(weight);
                temp = false;
            }
            weight = (float) (((Math.random()*(max-min))+min));
        }
        return randomFish;
    }

}
