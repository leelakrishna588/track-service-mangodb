package com.stackroute.springboottask.service;

import com.stackroute.springboottask.domain.Track;
import com.stackroute.springboottask.exceptions.TrackAlreadyExistException;
import com.stackroute.springboottask.exceptions.TrackNotFoundException;
import com.stackroute.springboottask.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceConsoleImpl implements TrackService {

    @Autowired
    private TrackRepository trackRepository;

    public TrackServiceConsoleImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistException {

        if (trackRepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistException("Track already exists");
        }
        Track savedTrack = trackRepository.save(track);
        if(savedTrack==null)
        {
            throw new TrackAlreadyExistException("Track already exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track updateTrackComments(String id, String comment) throws TrackNotFoundException {
        Track track=null;
        if(trackRepository.existsById(id))
        {
            track=trackRepository.findById(id).get();
            track.setTrackComments(comment);
            trackRepository.save(track);
        }
        else
        {
            throw new TrackNotFoundException("Track does not exist");
        }
        return track;
    }

    @Override
    public Track deleteTrack(String id) throws TrackNotFoundException {
        Track track=null;
        if(trackRepository.existsById(id))
        {
            track=trackRepository.findById(id).get();
            trackRepository.deleteById(id);
        }
        else
        {
            throw new TrackNotFoundException("Track does not exist");
        }
        return track;
    }
}
