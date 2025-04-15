package hu.OpenFishBackend.controller;

import hu.OpenFishBackend.dto.fish.FishDistance;
import hu.OpenFishBackend.dto.fish.RandomFish;
import hu.OpenFishBackend.model.Fish;
import hu.OpenFishBackend.service.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fish")
public class FishController {

    @Autowired
    private FishService fishService;

    @GetMapping("/getAll")
    public List<Fish> getFish(){
        return fishService.listAll();
    }


    @GetMapping("/{id}")
    public Fish getFishById(@PathVariable int id) {
        return fishService.getFishById(id);
    };


    //get a random fish by the distance it is called with
    @PostMapping("/getFishByDistance")
    public RandomFish getFishByDistance(@RequestBody FishDistance fishDistance){
        System.out.println(fishDistance.toString());
        return fishService.getFishByDistance(fishDistance);
    }


}
