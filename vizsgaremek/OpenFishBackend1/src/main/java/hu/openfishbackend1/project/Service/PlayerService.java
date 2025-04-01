package hu.openfishbackend1.project.Service;

import hu.openfishbackend1.project.Exceptions.PlayerNotFoundException;
import hu.openfishbackend1.project.Model.Player.CreatePlayer;
import hu.openfishbackend1.project.Model.Player.Player;
import hu.openfishbackend1.project.Model.Player.UpdatePlayer;
import hu.openfishbackend1.project.Converter.PlayerConverter;
import hu.openfishbackend1.repository.PlayerRepository;
import hu.openfishbackend1.project.dto.PlayerListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<PlayerListItem> listAll() {
    return PlayerConverter.convertModelsToDtoList(playerRepository.findAll());
    }


    public Player createPlayer(CreatePlayer createPlayer){
        Player player =playerRepository.save(PlayerConverter.convertCreateToModel(createPlayer));
        return PlayerConverter.convertModelToRead(player);
    }

    public Player readPlayer(int id){
        if(!playerRepository.existsById(id)){
            throw new PlayerNotFoundException();
        }
        return PlayerConverter.convertModelToRead(playerRepository.getReferenceById(id));
    }

    public Player updatePlayer(int id, UpdatePlayer player){
        if(!playerRepository.existsById(id)){
            throw new RuntimeException("Player not found");
        }
        Player newPlayer = playerRepository.save(PlayerConverter.convertUpdateToModel(id, player));
        return PlayerConverter.convertModelToRead(playerRepository.getReferenceById(id));
    }

    public Player deletePlayer(Integer id){
        Player deletingPlayer = readPlayer(id);
        playerRepository.deleteById(id);
        return deletingPlayer;
    }


}
