package hu.openfishbackend1.project.Converter;

import hu.openfishbackend1.project.Model.Fish.Fish;
import hu.openfishbackend1.project.dto.Fish.FishListItem;

import java.util.ArrayList;
import java.util.List;

public class FishConverter {

    public static List<FishListItem> convertModelsToDtoList(List<Fish> models) {
        List<FishListItem> fishListItems = new ArrayList<FishListItem>();
        models.forEach( model -> {fishListItems.add(convertModelToListItem(model));});
        return fishListItems;
    }

    private static FishListItem convertModelToListItem(Fish model) {
        FishListItem fishListItem = new FishListItem();

        fishListItem.setId(model.getId());
        fishListItem.setSpecies(model.getSpecies());
        fishListItem.setDescription(model.getDescription());
        fishListItem.setWiki(model.getWiki());
        fishListItem.setRarity(model.getRarity());
        fishListItem.setDistance(model.getDistance());
        fishListItem.setDifficulty(model.getDifficulty());

        return fishListItem;
    }

    public static Fish convertModelToRead(Fish fish){
        Fish newFish = new Fish();
        newFish.setId(fish.getId());
        newFish.setSpecies(fish.getSpecies());
        newFish.setDescription(fish.getDescription());
        newFish.setWiki(fish.getWiki());
        newFish.setRarity(fish.getRarity());
        newFish.setDistance(fish.getDistance());
        newFish.setDifficulty(fish.getDifficulty());
        return newFish;
    }

}
