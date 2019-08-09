package com.stackroute.springboottask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(value = TrackAlreadyExistException.class)
    public ResponseEntity<String> returnTrackAlredyExistsException(TrackAlreadyExistException trackAlreadyExistsException) {
        return new ResponseEntity<String>(trackAlreadyExistsException.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<String> returnTrackNotFoundException(TrackNotFoundException trackNotFoundException) {
        return new ResponseEntity<String>(trackNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
