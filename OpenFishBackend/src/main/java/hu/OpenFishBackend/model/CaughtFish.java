package hu.OpenFishBackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="caughtfish")
public class CaughtFish implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
//    @MapsId("id")
    @JoinColumn(name = "fish_id", nullable = false)
    private Fish fish;

    @ManyToOne
//    @MapsId("id")
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    private int amount;
    private float record;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Fish getFishId() {
        return fish;
    }

    public void setFishId(Fish fish) {
        this.fish = fish;
    }

    public Users getUserId() {
        return user;
    }

    public void setUserId(Users user) {
        this.user = user;
    }

    public float getRecord() {
        return record;
    }

    public void setRecord(float record) {
        this.record = record;
    }
}
