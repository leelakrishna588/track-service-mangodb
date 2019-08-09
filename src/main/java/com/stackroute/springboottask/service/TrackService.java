package com.stackroute.springboottask.service;

import com.stackroute.springboottask.domain.Track;
import com.stackroute.springboottask.exceptions.TrackAlreadyExistException;
import com.stackroute.springboottask.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistException;

    public List<Track> getAllTracks();

    public Track updateTrackComments(String id, String comment) throws TrackNotFoundException;

    public Track deleteTrack(String id) throws TrackNotFoundException;
}
