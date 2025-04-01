package hu.openfishbackend1.project.Converter;

import hu.openfishbackend1.project.Model.Player.CreatePlayer;
import hu.openfishbackend1.project.Model.Player.Player;
import hu.openfishbackend1.project.Model.Player.UpdatePlayer;
import hu.openfishbackend1.project.dto.PlayerListItem;

import java.util.ArrayList;
import java.util.List;

public class PlayerConverter {

    public static List<PlayerListItem> convertModelsToDtoList(List<Player> models) {
        List<PlayerListItem> playerListItems = new ArrayList<PlayerListItem>();
        models.forEach( model -> {playerListItems.add(convertModelToListItem(model));});
        return playerListItems;
    }

    private static PlayerListItem convertModelToListItem(Player model) {
        PlayerListItem playerListItem = new PlayerListItem();

        playerListItem.setId(model.getId());
        playerListItem.setName(model.getName());
        playerListItem.setEmail(model.getEmail());
        playerListItem.setPassword(model.getPassword());
        playerListItem.setPoints(model.getPoints());


        return playerListItem;
    }

    public static Player convertCreateToModel(CreatePlayer createPlayer) {
        Player player = new Player();
        player.setName(createPlayer.getName());
        player.setEmail(createPlayer.getEmail());
        player.setPassword(createPlayer.getPassword());
        player.setPoints(createPlayer.getPoints());
        return player;
    }

    public static Player convertModelToRead(Player player) {
        Player playerRead = new Player();
        playerRead.setId(player.getId());
        playerRead.setName(player.getName());
        playerRead.setEmail(player.getEmail());
        playerRead.setPassword(player.getPassword());
        playerRead.setPoints(player.getPoints());
        return playerRead;
    }

    public static Player convertUpdateToModel(Integer id, UpdatePlayer updatePlayer) {
        Player player = new Player();
        player.setId(id);
        player.setName(updatePlayer.getName());
        player.setEmail(updatePlayer.getEmail());
        player.setPassword(updatePlayer.getPassword());
        player.setPoints(updatePlayer.getPoints());
        return player;
    }


}
