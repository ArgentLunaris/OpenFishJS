//package hu.openfishbackend1.project.Converter;
//
//import hu.openfishbackend1.project.Model.CaughtFish.CaughtFish;
//import hu.openfishbackend1.project.dto.CaughtFish.CaughtFishItem;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CaughtFishConverter {
//
//    public static List<CaughtFishItem> convertModelsToDtoList(List<CaughtFish> models){
//        List<CaughtFishItem> caughtFishItems = new ArrayList<>();
//        models.forEach( model -> {caughtFishItems.add(convertModelToListItem(model));});
//        return caughtFishItems;
//    }
//
//    private static CaughtFishItem convertModelToListItem(CaughtFish model){
//        CaughtFishItem caughtFishItem = new CaughtFishItem();
//
//        caughtFishItem.setId(model.getId());
//        caughtFishItem.setAmount(model.getAmount());
//        return caughtFishItem;
//    }
//
//}
