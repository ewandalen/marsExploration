package com.contaazul.marsExploration.controller;

import com.contaazul.marsExploration.model.Robot;
import com.contaazul.marsExploration.service.MarsExplorationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mars")
public class MarsExplorationController {

    @Autowired
    private MarsExplorationService marsExplorationService;

    @PostMapping("/{command}")
    public ResponseEntity<String> mover(@PathVariable("command") final String command) {

        Robot robot = marsExplorationService.executeCommands(command);

        return ResponseEntity.status(200).body(robot.toString());
    }

}
