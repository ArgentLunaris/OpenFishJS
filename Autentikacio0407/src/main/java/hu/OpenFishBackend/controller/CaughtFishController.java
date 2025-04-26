package hu.OpenFishBackend.controller;

import hu.OpenFishBackend.dto.caughtfish.CaughtFishDto;
import hu.OpenFishBackend.dto.caughtfish.CaughtFishUserId;
import hu.OpenFishBackend.dto.caughtfish.UpdateCaughtfish;
import hu.OpenFishBackend.service.CaughtFishService;
//import hu.openfishbackend1.project.Service.CaughtFishService;
//import hu.openfishbackend1.project.dto.CaughtFish.CaughtFishDto;
//import hu.openfishbackend1.project.dto.CaughtFish.CreateCaughtFish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caughtfish")
public class CaughtFishController {

    @Autowired
    private CaughtFishService caughtFishService;


//    @GetMapping
//    public List<CaughtFishItem> getPlayers(){return caughtFishService.getAllCaughtFish();}



    @GetMapping("/all")
    public List<CaughtFishDto> getAllCaughtFish() {
        return caughtFishService.getAllCaughtFish();
    }

    @PostMapping("/addupdate")
    public CaughtFishDto addOrUpdateFish(@RequestBody UpdateCaughtfish caughtFishDto) {
        if(caughtFishService.playerCaughtFish(caughtFishDto)) {
            System.out.println("false");
            return caughtFishService.createCaughtFish(caughtFishDto);
        }else {
            System.out.println("true");
            CaughtFishDto ret = caughtFishService.updateCaughtFishAmount(caughtFishDto);
            ret.setRecord(caughtFishService.checkForRecord(caughtFishDto.getWeight(), caughtFishDto.getUserId(), caughtFishDto.getFishId()));
            return ret;
        }
        //return fishy :);
    }

    @PostMapping("/getAllForUser")
    public List<CaughtFishDto> getAllCaughtFishForUser(@RequestBody CaughtFishUserId user) {
        return caughtFishService.allCaughtFishForId(user);
    }

//    @PostMapping("/add")
//    public ResponseEntity<String> addCaughtFish(@RequestBody CaughtFishDto caughtFishDto) {
//        caughtFishService.createCaughtFish(caughtFishDto);
//        return ResponseEntity.ok("Caught fish record added successfully!");
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<String> updateCaughtFish(@RequestBody CaughtFishDto request) {
//        caughtFishService.updateCaughtFishAmount(request);
//        return ResponseEntity.ok("Caught fish amount updated successfully!");
//    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCaughtFish(@PathVariable("id") int id) {
        caughtFishService.deleteCaughtFishById(id);
        return ResponseEntity.ok("Caught fish record deleted successfully!");
    }

}
