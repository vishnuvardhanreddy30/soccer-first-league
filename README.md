# wealthpilot-first-league

This Spring Boot application generates a game schedule for a soccer league. The schedule is generated based on rules similar to German Bundesliga's.

## Features

- Generate game schedules where all teams play against each other.
- Games are scheduled only on Saturdays.
- A 3-week break is implemented between the first and second legs.
- Support for scheduling multiple games on the same Saturday.
- JSON input for team information.

### Prerequisites

- Java 11
- Maven

### Running the Application

1. Clone the repository:

    ```bash
    git clone git@github.com:vishnuvardhanreddy30/wealthpilot-first-league.git
    ```

2. Navigate to the project directory:

    ```bash
    cd wealthpilot-first-league
    ```

3. Build the project with Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

### Endpoints

- **Get Schedule (Single Game per Saturday):**

    ```
    POST /api/generate-schedule
    ```

- **Get Schedule (Multiple Games per Saturday):**

    ```
    POST /api/generate-schedule-multiple
    ```

## Example JSON Format

The JSON file `soccer_teams.json` should have the following format:

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
