package hu.OpenFishBackend.converter;

import hu.OpenFishBackend.dto.fish.RandomFish;
import hu.OpenFishBackend.model.Fish;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Random;

@Component
public class FishConverter {

    public RandomFish convertModelToRandom(Fish fish){
        RandomFish randomFish = new RandomFish();
        randomFish.setId(fish.getId());
        randomFish.setSpecies(fish.getSpecies());
        randomFish.setRarity(fish.getRarity());
        float min = fish.getMinWeight();
        float max = fish.getMaxWeight();
        randomFish.setWeight(randomWeight(min, max));
        return randomFish;
    }

    private float randomWeight(float min, float max){
        return (float) (Math.round((Math.random() * (max - min) + min) * 100.0) / 100.0);
    }

}
