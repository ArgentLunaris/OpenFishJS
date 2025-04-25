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
        float weight = randomWeight(min, max);
        boolean temp = true;
        while(temp) {
            if(weight < max && weight > min){

                randomFish.setWeight(weight);
                temp = false;
            }
            weight = randomWeight(min, max);
        }
        return randomFish;
    }

    private float randomWeight(float min, float max){
        Random r = new Random();
        DecimalFormat df = new DecimalFormat("#.##");
        return Float.parseFloat(df.format( r.nextFloat(min, max)));
    }

}
