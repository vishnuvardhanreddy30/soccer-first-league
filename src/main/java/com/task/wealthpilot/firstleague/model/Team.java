package com.task.wealthpilot.firstleague.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model representing a team in the league.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Team {
    private String name;
    @JsonProperty("founding_date")
    private String foundingDate;
}
