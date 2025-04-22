package hu.OpenFishBackend.dto.caughtfish;

import lombok.Data;


@Data
public class CaughtFishDto {

    private int userId;
    private int fishId;
    private int amount;

    public CaughtFishDto() {}

    public CaughtFishDto(int userId, int fishId, int amount) {
        this.userId = userId;
        this.fishId = fishId;
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "CaughtFishDto{" +
                "userId=" + userId +
                ", fishId=" + fishId +
                ", amount=" + amount +
                '}';
    }
}
