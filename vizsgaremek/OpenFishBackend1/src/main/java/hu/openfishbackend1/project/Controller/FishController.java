package hu.openfishbackend1.project.Controller;

import hu.openfishbackend1.project.Model.Fish.Fish;
import hu.openfishbackend1.project.Service.FishService;
import hu.openfishbackend1.project.dto.Fish.FishListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fish")
public class FishController {


    @Autowired
    private FishService fishService;

    @GetMapping
    public List<FishListItem> getFish(){
        return fishService.listAll();
    }

    @GetMapping("/{id}")
    public Fish getFishById(@PathVariable int id){
        return fishService.readFish(id);
    };




}
