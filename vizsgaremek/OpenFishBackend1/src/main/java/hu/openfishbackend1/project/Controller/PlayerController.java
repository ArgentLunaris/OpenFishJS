package hu.openfishbackend1.project.Controller;

import hu.openfishbackend1.project.Model.Player.CreatePlayer;
import hu.openfishbackend1.project.Model.Player.Player;
import hu.openfishbackend1.project.Model.Player.UpdatePlayer;
import hu.openfishbackend1.project.Service.PlayerService;
import hu.openfishbackend1.project.dto.PlayerListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<PlayerListItem> getPlayers(){
        return playerService.listAll();
    }

//
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable int id){
        return playerService.readPlayer(id);
    }

    @PostMapping
    public Player createPlayer(@RequestBody CreatePlayer createPlayer){
        return playerService.createPlayer(createPlayer);
    }


    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody UpdatePlayer uPlayer){
        return playerService.updatePlayer(id, uPlayer);
    }

    @DeleteMapping("/{id}")
    public Player deletePlayer(@PathVariable int id){
        return playerService.deletePlayer(id);
    }

}
