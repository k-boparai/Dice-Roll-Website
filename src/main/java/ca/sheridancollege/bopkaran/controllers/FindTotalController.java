package ca.sheridancollege.bopkaran.controllers;

import ca.sheridancollege.bopkaran.beans.Roll;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/findTotal")
public class FindTotalController {

    @RequestMapping(value = "/Dice", consumes = "application/json")
    public int diceTotal (@RequestBody List<Roll> results) {
        int total = 0;
        for (int i = 0; i < results.size(); i++) {
            total = total + results.get(i).rollValue;
        }
        return total;
    }

}
