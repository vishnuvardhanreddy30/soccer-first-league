package com.task.wealthpilot.firstleague.service.impl;

import com.task.wealthpilot.firstleague.model.LeagueResponse;
import com.task.wealthpilot.firstleague.model.Team;
import com.task.wealthpilot.firstleague.service.GameScheduleService;
import com.task.wealthpilot.firstleague.service.exception.GameScheduleServiceException;
import com.task.wealthpilot.firstleague.service.mapper.GameScheduleMapper;
import com.task.wealthpilot.firstleague.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameScheduleServiceImpl implements GameScheduleService {
    private final GameScheduleMapper gameScheduleMapper;
    private final List<String> schedule;

    @Value("${league.start.date}")
    private String leagueStartDate;

    @Override
    public LeagueResponse generateSchedule(List<Team> teams) {
        try {

            LocalDate START_DATE = LocalDate.parse(leagueStartDate).with(DayOfWeek.SATURDAY);

            // Generate matches with Round-robin for the first leg
            List<String> firstLegMatches = gameScheduleMapper.generateMatches(teams);
            // First leg
            for (String match : firstLegMatches) {
                schedule.add(DateUtil.formatDate(START_DATE) + " " + match);
                START_DATE = START_DATE.plusWeeks(1);
            }

            // Break between legs
            START_DATE = gameScheduleMapper.breakWeeks(START_DATE);

            // Generate matches for the second leg (reversed fixtures)
            List<String> secondLegMatches = gameScheduleMapper.generateReverseFixtures(firstLegMatches);

            // Second leg with reversed fixtures
            for (String match : secondLegMatches) {
                schedule.add(DateUtil.formatDate(START_DATE) + " " + match);
                START_DATE = START_DATE.plusWeeks(1);
            }

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
}
