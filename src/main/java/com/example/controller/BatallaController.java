package com.example.controller;

import com.example.entity.Batalla;
import com.example.service.BatallaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/batallas")
public class BatallaController {

    private final BatallaService batallaService;

    public BatallaController(BatallaService batallaService) {
        this.batallaService = batallaService;
    }

    @PostMapping
    public Batalla iniciarBatalla(@RequestParam Long pokemon1Id, @RequestParam Long pokemon2Id) {
        return batallaService.iniciarBatalla(pokemon1Id, pokemon2Id);
    }
}

