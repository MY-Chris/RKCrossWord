package com.acm.rossword.controller;

import com.acm.rossword.repository.ScoreRepository;
import com.acm.rossword.service.CrosswordGUI;
import com.acm.rossword.service.CrosswordPuzzleGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.*;
import java.util.List;

@RestController
public class GeneratorController {
    @Autowired
    CrosswordGUI gui;

    @Autowired
    ScoreRepository scoreRepo;


    @GetMapping("/crossword/create")
    public ResponseEntity HelloWorld() throws Exception {

        CrosswordPuzzleGenerator generator= new CrosswordPuzzleGenerator();
        new CrosswordGUI(generator.res,scoreRepo);
//        gui.temp(scoreRepo);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

}
