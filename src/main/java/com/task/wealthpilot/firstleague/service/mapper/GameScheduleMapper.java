package com.task.wealthpilot.firstleague.service.mapper;

import com.task.wealthpilot.firstleague.model.Team;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

@Component
public class GameScheduleMapper {
    @Value("${league.break.weeks}")
    private int breakWeeks;

    @Value("${league.matches.per.saturday}")
    private int league_matches;

    private AtomicInteger index = new AtomicInteger(1);

    public IntUnaryOperator calculateStartDate = (currentIndex) -> {
        if (currentIndex == league_matches) {
            index.set(1);
            return 1;
        } else {
            index.incrementAndGet();
            return 0;
        }
    };

    public List<String> generateMatches(List<Team> teams) {
        List<String> matches = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                matches.add(teams.get(i).getName() + " vs " + teams.get(j).getName());
            }
        }
        Collections.shuffle(matches); // Shuffle matches for randomness
        return matches;
    }
    public List<String> generateReverseFixtures(List<String> firstLegMatches) {
        List<String> reversed = new ArrayList<>();
        for (String match : firstLegMatches) {
            String[] teams = match.split(" vs ");
            reversed.add(teams[1] + " vs " + teams[0]);
        }
        return reversed;
    }

    public LocalDate breakWeeks(LocalDate NEW_START_DATE) {
        return NEW_START_DATE.plusWeeks(breakWeeks);
    }

    public int calculateNewStartDate() {
        return calculateStartDate.applyAsInt(index.get());
    }
}
