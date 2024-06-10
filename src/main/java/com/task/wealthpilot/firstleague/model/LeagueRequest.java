package com.task.wealthpilot.firstleague.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LeagueRequest {

    private String league;
    private String country;
    private List<Team> teams;
}
