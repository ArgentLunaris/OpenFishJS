package hu.openfishbackend1.project.Model.Player;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeletePlayer {
    private int id;
    private String name;
    private String email;
    private String password;
    private int points;



}
