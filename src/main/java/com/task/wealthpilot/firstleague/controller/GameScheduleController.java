package com.task.wealthpilot.firstleague.controller;

import com.task.wealthpilot.firstleague.model.LeagueRequest;
import com.task.wealthpilot.firstleague.model.LeagueResponse;
import com.task.wealthpilot.firstleague.service.GameScheduleService;
import com.task.wealthpilot.firstleague.service.exception.GameScheduleServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * REST Controller for managing game schedules.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
@RequestMapping("/api")
@Tag(name = "Game Schedule", description = "API for generating game schedules")
public class GameScheduleController {

    private final GameScheduleService gameScheduleService;

    /**
     * POST /generate-schedule : Generate a game schedule for the league.
     *
     * This endpoint generates a game schedule based on the list of teams provided in the request body.
     * The schedule will follow a round-robin format, with all matches taking place on Saturdays.
     * There will be a 3-week break between the first and second legs.
     *
     * @param league the LeagueRequest containing the list of teams.
     * @return a CompletableFuture with a LeagueResponse containing the generated schedule.
     * @throws GameScheduleServiceException if there is an error generating the schedule.
     */
    @PostMapping("/generate-schedule")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Generate a game schedule", description = "Generates a game schedule based on the list of teams provided")
    public CompletableFuture<LeagueResponse> generateSchedule(@RequestBody LeagueRequest league) {

        return CompletableFuture.supplyAsync(() -> gameScheduleService.generateSchedule(league.getTeams()));
    }
}
