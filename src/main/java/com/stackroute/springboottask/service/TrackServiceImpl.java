package com.stackroute.springboottask.service;

import com.stackroute.springboottask.domain.Track;
import com.stackroute.springboottask.exceptions.TrackAlreadyExistException;
import com.stackroute.springboottask.exceptions.TrackNotFoundException;
import com.stackroute.springboottask.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import sun.tools.java.Environment;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class TrackServiceImpl implements  TrackService{

    @Autowired
    private TrackRepository trackRepository;
    private Track track;
    @Autowired
    private Environment environment;

    public TrackServiceImpl(TrackRepository trackRepository){
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistException {
        if (trackRepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistException(environment.getProperty("trackalreadyexistsexception.message"));
        }
        Track savedTrack = trackRepository.save(track);
        if(savedTrack==null)
        {
            throw new TrackAlreadyExistException("trackalreadyexistsexception.message");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();

    }

    @Override
    public Track updateTrackComments(String id, String comment) throws TrackNotFoundException {
        Optional optional=trackRepository.findById(id);
        if(optional.isPresent())
        {
            track=trackRepository.findById(id).get();
            track.setTrackComments(comment);
            trackRepository.save(track);
        }
        else
        {
            throw new TrackNotFoundException(environment.getProperty("tracknotfoundexception.message"));
        }
        return track;
    }

    @Override
    public Track deleteTrack(String id) throws TrackNotFoundException {
        Track track=null;
        Optional optional=trackRepository.findById(id);
        if(optional.isPresent())
        {
            track=trackRepository.findById(id).get();
            trackRepository.deleteById(id);
        }
        else
        {
            throw new TrackNotFoundException(environment.getP("trackalreadyexistsexception.message"));
        }
        return track;
    }
}
