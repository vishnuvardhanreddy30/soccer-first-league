package com.task.soccer.firstleague.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GameScheduleServiceException extends RuntimeException {
    public GameScheduleServiceException(String message) {
        super(message);
    }
}
