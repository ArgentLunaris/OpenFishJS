package hu.openfishbackend1.project.Model.Player;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.processing.Pattern;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    private String name;
    //@Pattern("/^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$/g")
    private String email;
    private String password;
    private int points;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return points;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}


