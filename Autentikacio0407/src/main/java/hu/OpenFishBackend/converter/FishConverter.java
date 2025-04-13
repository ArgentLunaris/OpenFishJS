package hu.OpenFishBackend.converter;

import hu.OpenFishBackend.dto.fish.RandomFish;
import hu.OpenFishBackend.model.Fish;

public class FishConverter {

    public RandomFish convertModelToRandom(Fish fish){
        RandomFish randomFish = new RandomFish();
        randomFish.setSpecies(fish.getSpecies());
        randomFish.setRarity(fish.getRarity());
        float min = fish.getMinWeight();
        float max = fish.getMaxWeight();
        float weight = 0;
        boolean temp = true;
        while(temp) {
            if(weight < max && weight > min){
                randomFish.setWeight(weight);
                temp = false;
            }
        }
        randomFish.setWeight(weight);
        return randomFish;
    }

}
