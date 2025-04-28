package hu.OpenFishBackend.dto.users;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUsers {
    private String username;
    private String email;
    private String password;



}
