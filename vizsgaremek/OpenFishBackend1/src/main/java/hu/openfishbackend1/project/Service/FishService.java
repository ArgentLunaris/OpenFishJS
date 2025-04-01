package hu.openfishbackend1.project.Service;

import hu.openfishbackend1.project.Converter.FishConverter;
import hu.openfishbackend1.project.Exceptions.FishNotFoundException;
import hu.openfishbackend1.repository.FishRepository;
import hu.openfishbackend1.project.Model.Fish.Fish;
import hu.openfishbackend1.project.dto.Fish.FishListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishService {

    @Autowired
    private FishRepository fishRepository;

    public List<FishListItem> listAll() {
        return FishConverter.convertModelsToDtoList(fishRepository.findAll());
    }

    public Fish readFish(Integer id) {
        if (!fishRepository.existsById(id)) {
            throw new FishNotFoundException();}

            return FishConverter.convertModelToRead(fishRepository.getReferenceById(id));
        }

}
