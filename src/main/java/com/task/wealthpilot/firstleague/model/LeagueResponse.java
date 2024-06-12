package com.task.wealthpilot.firstleague.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response object containing the generated game schedule.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LeagueResponse {
    List<String> leagueSchedule;
}
