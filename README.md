# wealthpilot-first-league

This Spring Boot application generates a game schedule for a soccer league. The schedule is generated based on rules similar to German Bundesliga's.

## Features

- Generate game schedules where all teams play against each other.
- Games are scheduled only on Saturdays.
- A 3-week break is implemented between the first and second legs.
- Support for scheduling multiple games on the same Saturday.
- JSON input for team information.

  ```
  {
    "league": "Wealthpilot first league",
    "country": "Germany",
    "teams": [
      {
        "name": "Volksbank Kickers",
        "founding_date": "1998"
      },
      {
        "name": "Arminia Sparkasse",
        "founding_date": "2000"
      },
      {
        "name": "DJ FC"
      },
      {
        "name": "1. FC Marco"
      },
      {
        "name": "Borussia Helvetia",
        "founding_date": "2001"
      },
      {
        "name": "SC Graz"
      }
    ]
  }
