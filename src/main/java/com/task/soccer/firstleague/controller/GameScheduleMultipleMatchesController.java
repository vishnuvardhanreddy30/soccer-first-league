package com.task.soccer.firstleague.controller;

import com.task.soccer.firstleague.model.LeagueRequest;
import com.task.soccer.firstleague.model.LeagueResponse;
import com.task.soccer.firstleague.service.GameScheduleMultipleMatchesService;
import com.task.soccer.firstleague.service.exception.GameScheduleServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * REST Controller for managing game schedules Multiple Matches Per Week.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
@RequestMapping("/api")
@Tag(name = "Game Schedule Multiple Matches Per Week", description = "API for generating game schedules Multiple Matches Per Week")
public class GameScheduleMultipleMatchesController {
    private final GameScheduleMultipleMatchesService generateScheduleMultipleMatchesPerWeek;

    /**
     * POST /generate-schedule-multiple : Generate a game schedule Multiple Matches Per Week for the league.
     *
     * This endpoint generates a game schedule Multiple Matches Per Week based on the list of teams provided in the request body.
     * The schedule will follow a round-robin format, with all matches taking place on Saturdays.
     * There will be a 3-week break between the first and second legs.
     *
     * @param league the LeagueRequest containing the list of teams.
     * @return a CompletableFuture with a LeagueResponse containing the generated schedule.
     * @throws GameScheduleServiceException if there is an error generating the schedule.
     */
    @PostMapping("/generate-schedule-multiple")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Generate a game schedule Multiple Matches Per Week", description = "Generates a game schedule Multiple Matches Per Week based on the list of teams provided")
    public CompletableFuture<LeagueResponse> generateScheduleMultipleMatchesPerWeek(@RequestBody LeagueRequest league) {
        return CompletableFuture.supplyAsync(() -> generateScheduleMultipleMatchesPerWeek.generateScheduleMultipleMatchesPerWeek(league.getTeams()));
    }
}
