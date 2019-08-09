package com.stackroute.springboottask.controller;


import com.stackroute.springboottask.domain.Track;
import com.stackroute.springboottask.exceptions.TrackAlreadyExistException;
import com.stackroute.springboottask.exceptions.TrackNotFoundException;
import com.stackroute.springboottask.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TrackController {

    @Autowired
    private TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistException {
        ResponseEntity responseEntity;
        Track savedTrack = null;
        savedTrack = trackService.saveTrack(track);
        responseEntity = new ResponseEntity<Track>(savedTrack, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks()throws  Exception{
        return new ResponseEntity<List>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @PutMapping("track/{id}/{comment}")
    public ResponseEntity<?> getTrackAfterUpdatingComments(@PathVariable String id, @PathVariable String comment) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<Track>(trackService.updateTrackComments(id, comment), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> getTrackAfterDeleting(@PathVariable String id)throws TrackNotFoundException {
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<Track>(trackService.deleteTrack(id), HttpStatus.OK);
        return responseEntity;
    }
}
