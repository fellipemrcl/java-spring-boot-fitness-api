package com.betrybe.fitness.controller;

import com.betrybe.fitness.service.FitnessService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fitness controller.
 */
@RestController
@RequestMapping("/fitness")
public class FitnessController implements FitnessControllerInterface {

  FitnessService fitnessService;

  public FitnessController(FitnessService fitnessService) {
    this.fitnessService = fitnessService;
  }
}
