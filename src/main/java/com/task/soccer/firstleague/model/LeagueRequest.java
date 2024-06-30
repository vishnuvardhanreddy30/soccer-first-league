package com.task.soccer.firstleague.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request object for generating a game schedule.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LeagueRequest {

    private String league;
    private String country;
    private List<Team> teams;
}
