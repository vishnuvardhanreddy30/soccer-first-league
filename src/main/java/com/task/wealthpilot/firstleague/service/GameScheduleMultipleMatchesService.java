package com.task.wealthpilot.firstleague.service;

import com.task.wealthpilot.firstleague.model.LeagueResponse;
import com.task.wealthpilot.firstleague.model.Team;

import java.util.List;

public interface GameScheduleMultipleMatchesService {
    LeagueResponse generateScheduleMultipleMatchesPerWeek(List<Team> teams);
}
