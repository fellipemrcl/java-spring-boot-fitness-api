package com.betrybe.fitness.controller;

import com.betrybe.fitness.dto.WorkoutCreationDto;
import com.betrybe.fitness.dto.WorkoutDto;
import com.betrybe.fitness.service.FitnessServiceInterface;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fitness controller.
 */
@RestController
@RequestMapping("/fitness")
public class FitnessController implements FitnessControllerInterface {

  FitnessServiceInterface fitnessService;

  @Autowired
  public FitnessController(FitnessServiceInterface fitnessService) {
    this.fitnessService = fitnessService;
  }

  @GetMapping
  public String getRoot() {
    return "Boas vindas à API de Fitness!";
  }

  /**
   * Gets workout.
   *
   * @param id the id
   * @return the workout
   */
  @GetMapping("/workouts/{id}")
  public ResponseEntity<WorkoutDto> getWorkout(@PathVariable Long id) {
    Optional<WorkoutDto> workout = fitnessService.getWorkout(id);

    return workout.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

  }

  /**
   * Save workout response entity.
   *
   * @param workoutDto the workout dto
   * @return the response entity
   */
  @PostMapping("/workouts")
  public ResponseEntity<WorkoutDto> saveWorkout(@RequestBody WorkoutCreationDto workoutDto) {
    return ResponseEntity.status(201).body(fitnessService.saveWorkout(workoutDto));
  }
}
