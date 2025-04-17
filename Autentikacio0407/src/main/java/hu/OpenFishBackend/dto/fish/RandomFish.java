package hu.OpenFishBackend.dto.fish;

import lombok.Data;

@Data
public class RandomFish {
    private int id;
    private String species;
    private String rarity;

    //meg lesz adva
    private float weight;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
