package com.kian.game.controller;

import com.kian.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public String showGame(ModelMap model) {
        model.put("mancala", gameService.createGame());
        return "game";
    }
}
