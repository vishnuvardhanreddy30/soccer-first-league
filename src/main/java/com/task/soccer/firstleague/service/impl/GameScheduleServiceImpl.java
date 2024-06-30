package com.task.soccer.firstleague.service.impl;

import com.task.soccer.firstleague.model.LeagueResponse;
import com.task.soccer.firstleague.model.Team;
import com.task.soccer.firstleague.service.GameScheduleService;
import com.task.soccer.firstleague.utils.DateUtil;
import com.task.soccer.firstleague.service.exception.GameScheduleServiceException;
import com.task.soccer.firstleague.service.mapper.GameScheduleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of the GameScheduleService.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GameScheduleServiceImpl implements GameScheduleService {
    private final GameScheduleMapper gameScheduleMapper;
    private final List<String> schedule;

    @Value("${league.start.date}")
    String leagueStartDate;

    @Value("${league.break.weeks}")
    Integer breakWeeks;

    @Override
    public LeagueResponse generateSchedule(List<Team> teams) {
        try {
            LocalDate START_DATE = DateUtil.getNextSaturday(leagueStartDate);

            // Generate matches with Round-robin for the first leg
            List<String> firstLegMatches = gameScheduleMapper.generateMatches(teams);
            addMatchesToSchedule(firstLegMatches, START_DATE);

            // Break between legs
            START_DATE = DateUtil.addWeeks(START_DATE, breakWeeks+firstLegMatches.size());

            // Generate matches for the second leg (reversed fixtures)
            List<String> secondLegMatches = gameScheduleMapper.generateReverseFixtures(firstLegMatches);
            addMatchesToSchedule(secondLegMatches, START_DATE);

            // Output the schedule
            schedule.forEach(System.out::println);
            return LeagueResponse.builder().leagueSchedule(schedule).build();
        } catch (Exception ex) {
            // Log the exception
            log.error("Error generating schedule: {}", ex.getMessage(), ex);
            // Throw a custom exception
            throw new GameScheduleServiceException("Error generating schedule. Please try again later.");
        }
    }
    private void addMatchesToSchedule(List<String> matches, LocalDate startDate) {
        for (String match : matches) {
            schedule.add(DateUtil.formatDate(startDate) + " " + match);
            startDate = startDate.plusWeeks(1);
        }
    }
}
