package hu.openfishbackend1.project.dto.CaughtFish;

public class CaughtFishDto {

    private Long playerId;
    private Long fishId;
    private int amount;

    public CaughtFishDto(Long playerId, Long fishId, int amount) {
        this.playerId = playerId;
        this.fishId = fishId;
        this.amount = amount;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getFishId() {
        return fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
