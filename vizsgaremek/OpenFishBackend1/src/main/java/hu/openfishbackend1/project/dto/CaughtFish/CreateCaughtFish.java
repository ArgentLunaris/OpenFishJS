package hu.openfishbackend1.project.dto.CaughtFish;

import lombok.Data;

@Data

public class CreateCaughtFish {

    private int playerId;
    private int fishId;
    private int amount;

    public CreateCaughtFish(int playerId, int fishId, int amount) {
        this.playerId = playerId;
        this.fishId = fishId;
        this.amount = amount;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getFishId() {
        return fishId;
    }

    public void setFishId(int fishId) {
        this.fishId = fishId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
