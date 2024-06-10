package com.task.wealthpilot.firstleague.controller;

import com.task.wealthpilot.firstleague.model.LeagueRequest;
import com.task.wealthpilot.firstleague.model.LeagueResponse;
import com.task.wealthpilot.firstleague.service.GameScheduleMultipleMatchesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
@RequestMapping("/api")
public class GameScheduleMultipleMatchesController {
    private final GameScheduleMultipleMatchesService generateScheduleMultipleMatchesPerWeek;

    @PostMapping("/generate-schedule-multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<LeagueResponse> generateScheduleMultipleMatchesPerWeek(@RequestBody LeagueRequest league) {
        return CompletableFuture.supplyAsync(() -> generateScheduleMultipleMatchesPerWeek.generateScheduleMultipleMatchesPerWeek(league.getTeams()));
    }
}
