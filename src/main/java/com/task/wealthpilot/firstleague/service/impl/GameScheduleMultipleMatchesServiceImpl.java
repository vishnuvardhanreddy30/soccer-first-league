package com.task.wealthpilot.firstleague.service.impl;

import com.task.wealthpilot.firstleague.model.LeagueResponse;
import com.task.wealthpilot.firstleague.model.Team;
import com.task.wealthpilot.firstleague.service.GameScheduleMultipleMatchesService;
import com.task.wealthpilot.firstleague.service.exception.GameScheduleServiceException;
import com.task.wealthpilot.firstleague.service.mapper.GameScheduleMapper;
import com.task.wealthpilot.firstleague.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of the GameScheduleMultipleMatchesService.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GameScheduleMultipleMatchesServiceImpl implements GameScheduleMultipleMatchesService {
    @Value("${league.start.date}")
    String leagueStartDate;

    @Value("${league.break.weeks}")
    Integer breakWeeks;

    private final GameScheduleMapper gameScheduleMapper;
    private final List<String> schedule;

    //Generate Schedule for Multiple Matches PerWeek
    @Override
    public LeagueResponse generateScheduleMultipleMatchesPerWeek(List<Team> teams) {
        try {
            LocalDate START_DATE = DateUtil.getNextSaturday(leagueStartDate);

            // Generate matches with Round-robin for the first leg
            List<String> firstLegMatches = gameScheduleMapper.generateMatches(teams);
            addMatchesToSchedule(firstLegMatches, START_DATE);

            // Break between legs
            START_DATE = DateUtil.addWeeks(START_DATE,breakWeeks);

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

    private void addMatchesToSchedule(List<String> matches, LocalDate START_DATE) {
        for (String match : matches) {
            schedule.add(DateUtil.formatDate(START_DATE) + " " + match);
            START_DATE = START_DATE.plusWeeks(gameScheduleMapper.calculateNewStartDate());
        }
    }
}
