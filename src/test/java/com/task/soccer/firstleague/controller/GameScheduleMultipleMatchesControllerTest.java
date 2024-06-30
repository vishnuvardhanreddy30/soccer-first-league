package com.task.soccer.firstleague.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.soccer.firstleague.model.LeagueRequest;
import com.task.soccer.firstleague.model.Team;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class GameScheduleMultipleMatchesControllerTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGenerateScheduleTes() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/generate-schedule-multiple")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(leagueRequest()))
        ).andExpect(status().isCreated());
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
}
