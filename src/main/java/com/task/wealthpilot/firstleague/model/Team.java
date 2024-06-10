package com.task.wealthpilot.firstleague.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Team {
    private String name;
    @JsonProperty("founding_date")
    private String foundingDate;
}
