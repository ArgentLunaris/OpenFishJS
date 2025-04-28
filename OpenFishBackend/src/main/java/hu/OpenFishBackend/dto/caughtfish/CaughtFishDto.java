package hu.OpenFishBackend.dto.caughtfish;

import lombok.Data;


@Data
public class CaughtFishDto {

    private int userId;
    private int fishId;
    private int amount;
    private float record;

    public CaughtFishDto() {}

    public CaughtFishDto(int userId, int fishId, int amount, float record) {
        this.userId = userId;
        this.fishId = fishId;
        this.amount = amount;
        this.record = record;
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

    public float getRecord() {
        return record;
    }

    public void setRecord(float record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "CaughtFishDto{" +
                "amount=" + amount +
                ", userId=" + userId +
                ", fishId=" + fishId +
                ", record=" + record +
                '}';
    }
}
