package com.stackroute.springboottask.prefill;

import com.stackroute.springboottask.domain.Track;
import com.stackroute.springboottask.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerDemo implements CommandLineRunner {

    @Value("${track2.id}")
    private String id;

    @Value("${track2.name}")
    private String name;

    @Value("${track2.comment}")
    private String comment;

    @Autowired
    TrackRepository trackRepository;
    Track track = new Track();

    @Override
    public void run(String... args) throws Exception {

        track.setTrackId(id);
        track.setTrackname(name);
        track.setTrackComments(comment);
        trackRepository.save(track);
    }
}
