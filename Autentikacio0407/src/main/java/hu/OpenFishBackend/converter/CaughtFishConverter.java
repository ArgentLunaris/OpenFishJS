package hu.OpenFishBackend.converter;

import hu.OpenFishBackend.dto.caughtfish.CaughtFishDto;
import hu.OpenFishBackend.model.CaughtFish;
import org.springframework.stereotype.Component;

@Component
public class CaughtFishConverter {

    public CaughtFishDto caughtFishToDto(CaughtFish caughtFish) {
        CaughtFishDto c = new CaughtFishDto();
        c.setFishId(caughtFish.getFishId().getId());
        c.setUserId(caughtFish.getUserId().getId());
        c.setAmount(caughtFish.getAmount());
        c.setRecord(caughtFish.getRecord());
        return c;
    }

}
