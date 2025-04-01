package hu.openfishbackend1.project.Model.CaughtFish;

import hu.openfishbackend1.project.Model.Fish.Fish;
import hu.openfishbackend1.project.Model.Player.Player;
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

    private int amount;

    @ManyToOne
    @JoinColumn(name = "fish_id", nullable = false)
    private Fish fish;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;


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

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
