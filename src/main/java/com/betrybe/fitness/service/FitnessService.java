package com.betrybe.fitness.service;

import com.betrybe.fitness.database.FakeFitnessDatabase;
import com.betrybe.fitness.dto.WorkoutCreationDto;
import com.betrybe.fitness.dto.WorkoutDto;
import com.betrybe.fitness.model.Workout;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Fitness service.
 */
@Service
public class FitnessService implements FitnessServiceInterface {

  FakeFitnessDatabase database;

  @Autowired
  public FitnessService(FakeFitnessDatabase database) {
    this.database = database;
  }

  @Override
  public WorkoutDto saveWorkout(WorkoutCreationDto newWorkoutDto) {
    Workout newWorkout = new Workout();
    newWorkout.setName(newWorkoutDto.name());
    newWorkout.setRepetitions(newWorkoutDto.repetitions());
    newWorkout.setSecretTechnique(newWorkoutDto.secretTechnique());

    Workout savedWorkout = database.saveWorkout(newWorkout);

    return new WorkoutDto(
        savedWorkout.getId(),
        savedWorkout.getName(),
        savedWorkout.getRepetitions()
    );
  }

  @Override
  public Optional<WorkoutDto> getWorkout(Long id) {
    Optional<Workout> optWorkout = database.getWorkout(id);

    if (optWorkout.isEmpty()) {
      return Optional.empty();
    }

    Workout workout = optWorkout.get();

    WorkoutDto workoutDto = new WorkoutDto(
        workout.getId(),
        workout.getName(),
        workout.getRepetitions()
    );

    return Optional.of(workoutDto);
  }

  @Override
  public List<WorkoutDto> getAllWorkouts() {
    return database.getAllWorkouts().stream()
        .map(workout -> new WorkoutDto(
            workout.getId(),
            workout.getName(),
            workout.getRepetitions()
        ))
        .toList();
  }
}
