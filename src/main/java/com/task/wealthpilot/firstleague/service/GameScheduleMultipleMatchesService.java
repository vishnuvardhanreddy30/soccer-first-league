package com.task.wealthpilot.firstleague.service;

import com.task.wealthpilot.firstleague.model.LeagueResponse;
import com.task.wealthpilot.firstleague.model.Team;
import com.task.wealthpilot.firstleague.service.exception.GameScheduleServiceException;

import java.util.List;

/**
 * Service interface for generating game schedules Multiple Matches Per Week.
 */
public interface GameScheduleMultipleMatchesService {
    /**
     * Generate a game schedule Multiple Matches Per Week for the provided list of teams.
     *
     * @param teams the list of teams to include in the schedule.
     * @return a LeagueResponse containing the generated schedule.
     * @throws GameScheduleServiceException if there is an error generating the schedule.
     */
    LeagueResponse generateScheduleMultipleMatchesPerWeek(List<Team> teams);
}
