package hu.OpenFishBackend.dto;

import lombok.Data;

@Data
public class FishDistance {

    private int distance;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
