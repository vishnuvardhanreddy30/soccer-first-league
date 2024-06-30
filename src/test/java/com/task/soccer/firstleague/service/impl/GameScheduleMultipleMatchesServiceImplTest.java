package com.task.soccer.firstleague.service.impl;

import com.task.soccer.firstleague.model.LeagueRequest;
import com.task.soccer.firstleague.model.LeagueResponse;
import com.task.soccer.firstleague.model.Team;
import com.task.soccer.firstleague.service.mapper.GameScheduleMapper;
import com.task.soccer.firstleague.utils.DateUtil;
import com.task.soccer.firstleague.service.exception.GameScheduleServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class GameScheduleMultipleMatchesServiceImplTest {

    @Value("${league.start.date}")
    private String leagueStartDate;
    @Value("${league.break.weeks}")
    int breakWeeks;
    @Mock
    private GameScheduleMapper gameScheduleMapper;

    @InjectMocks
    private GameScheduleMultipleMatchesServiceImpl gameScheduleService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<String> schedule = new ArrayList<>();
        gameScheduleService = new GameScheduleMultipleMatchesServiceImpl(gameScheduleMapper, schedule);
        gameScheduleService.leagueStartDate = leagueStartDate;
        gameScheduleService.breakWeeks = breakWeeks;
    }

    @Test
    public void testGenerateScheduleMultipleMatchesPerWeek() {
        var teams = leagueRequest().getTeams();

        List<String> firstLegMatches = new ArrayList<>();
        firstLegMatches.add("DJ FC vs SC Graz");
        firstLegMatches.add("Volksbank Kickers vs Arminia Sparkasse");
        firstLegMatches.add("1. FC Marco vs Borussia Helvetia");
        firstLegMatches.add("Borussia Helvetia vs SC Graz");

        List<String> secondLegMatches = new ArrayList<>();
        secondLegMatches.add("DJ FC vs 1. FC Marco");
        secondLegMatches.add("Arminia Sparkasse vs 1. FC Marco");
        secondLegMatches.add("Borussia Helvetia vs DJ FC");
        secondLegMatches.add("Arminia Sparkasse vs Volksbank Kickers");


        when(gameScheduleMapper.generateMatches(teams)).thenReturn(firstLegMatches);
        when(gameScheduleMapper.generateReverseFixtures(firstLegMatches)).thenReturn(secondLegMatches);
        when(gameScheduleMapper.calculateNewStartDate()).thenReturn(1);

        LeagueResponse response = gameScheduleService.generateScheduleMultipleMatchesPerWeek(teams);

        verify(gameScheduleMapper, times(1)).generateMatches(teams);
        verify(gameScheduleMapper, times(1)).generateReverseFixtures(firstLegMatches);
        verify(gameScheduleMapper, times(8)).calculateNewStartDate();

        List<String> expectedSchedule = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(leagueStartDate).with(java.time.DayOfWeek.SATURDAY);
        for (String match : firstLegMatches) {
            expectedSchedule.add(DateUtil.formatDate(startDate) + " " + match);
            startDate = startDate.plusWeeks(1);
        }
        startDate = startDate.plusWeeks(3);  // Adding break between legs
        for (String match : secondLegMatches) {
            expectedSchedule.add(DateUtil.formatDate(startDate) + " " + match);
            startDate = startDate.plusWeeks(1);
        }

        assertEquals(expectedSchedule, response.getLeagueSchedule());
    }

    private LeagueRequest leagueRequest() {
        return LeagueRequest.builder()
                .league("Soccer first league")
                .country("Germany")
                .teams(Arrays.asList(
                        Team.builder().name("Volksbank Kickers").foundingDate("1998").build(),
                        Team.builder().name("Arminia Sparkasse").foundingDate("2000").build(),
                        Team.builder().name("DJ FC").build(),
                        Team.builder().name("1. FC Marco").build(),
                        Team.builder().name("Borussia Helvetia").foundingDate("2001").build(),
                        Team.builder().name("SC Graz").build()
                ))
                .build();
    }

    @Test(expected = GameScheduleServiceException.class)
    public void testGenerateScheduleMultipleMatchesPerWeek_Exception() {
        List<Team> teams = new ArrayList<>();
        when(gameScheduleMapper.generateMatches(teams)).thenThrow(new RuntimeException());

        gameScheduleService.generateScheduleMultipleMatchesPerWeek(teams);
    }
}

