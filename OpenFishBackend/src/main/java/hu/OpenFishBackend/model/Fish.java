package hu.OpenFishBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String species;
    private String description;
    private String rarity;
    private String wiki;
    private int distance;
    @Column(name="min_weight")
    private float minWeight;
    @Column(name="max_weight")
    private float maxWeight;

    public Fish(int id, String species, String description, String wiki, String rarity, int distance, float minWeight, float maxWeight) {
        this.id = id;
        this.species = species;
        this.description = description;
        this.wiki = wiki;
        this.rarity = rarity;
        this.distance = distance;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public float getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(float minWeight) {
        this.minWeight = minWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "id=" + id +
                ", species='" + species + '\'' +
                ", desc='" + description + '\'' +
                ", wiki='" + wiki + '\'' +
                ", rarity='" + rarity + '\'' +
                ", distance=" + distance +
                ", minWeight=" + minWeight +
                ", maxWeight=" + maxWeight +
                '}';
    }
}
