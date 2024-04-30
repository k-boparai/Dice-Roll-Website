package ca.sheridancollege.bopkaran.controllers;

import ca.sheridancollege.bopkaran.beans.Dice;
import ca.sheridancollege.bopkaran.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    DatabaseAccess da;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/rollDice")
    public String rollDice (Model model, @RequestParam int num, @RequestParam String type, RestTemplate restTemplate) {
        if (num < 1 || type.equals(null) || type.equals("")) {
            return "error";
        }
        if (num > 0) {
            int faces = Integer.valueOf(type);
            for (int i = 0; i < num; i++){
                int result = (int) (Math.random()*faces+1);
                da.insertRoll(i+1, result);
            }
            ResponseEntity<Integer> total = restTemplate.postForEntity("http://localhost:8080/findTotal/Dice", da.getRollList(), Integer.class);
            int totalValue = (int) total.getBody();
            da.insertDice(new Dice(num, faces, totalValue));
        }
        return "redirect:/rollResult";
    }

    @GetMapping("/rollResult")
    public String rollResults (Model model, @ModelAttribute Dice dice) {
        model.addAttribute("rollList", da.getRollList());
        model.addAttribute("diceList", da.getDiceList());
        return "rollResult";
    }

    @GetMapping("/newRoll")
    public String newRoll (Model model) {
        da.diceDelete();
        da.rollDelete();
        return "redirect:/";
    }

    @GetMapping("/sameAgain")
    public String sameAgain (Model model, RestTemplate restTemplate) {
        da.rollDelete();
        Dice dice = da.getDiceList().get(0);
        da.diceDelete();
        int num = dice.diceNumber;
        int faces = Integer.valueOf(dice.diceFaces);
        if (num > 0) {
            for (int i = 0; i < num; i++){
                int result = (int) (Math.random()*faces+1);
                da.insertRoll(i+1, result);
            }
            ResponseEntity<Integer> total = restTemplate.postForEntity("http://localhost:8080/findTotal/Dice", da.getRollList(), Integer.class);
            int totalValue = (int) total.getBody();
            da.insertDice(new Dice(num, faces, totalValue));
        }
        return "redirect:/rollResult";
    }
}
