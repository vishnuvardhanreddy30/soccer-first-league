package com.task.wealthpilot.firstleague.controller;

import com.task.wealthpilot.firstleague.model.LeagueRequest;
import com.task.wealthpilot.firstleague.model.LeagueResponse;
import com.task.wealthpilot.firstleague.service.GameScheduleService;
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
public class GameScheduleController {

    private final GameScheduleService gameScheduleService;

    @PostMapping("/generate-schedule")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<LeagueResponse> generateSchedule(@RequestBody LeagueRequest league) {

        return CompletableFuture.supplyAsync(() -> gameScheduleService.generateSchedule(league.getTeams()));
    }
}
