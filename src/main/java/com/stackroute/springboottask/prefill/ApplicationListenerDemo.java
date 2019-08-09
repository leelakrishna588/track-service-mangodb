package com.stackroute.springboottask.prefill;

import com.stackroute.springboottask.domain.Track;
import com.stackroute.springboottask.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;



    @Component
    public class ApplicationListenerDemo implements ApplicationListener<ContextRefreshedEvent> {

        private Environment environment;
        @Autowired
        TrackRepository trackRepository;

        Track track = new Track();

        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            track.setTrackId(environment.getProperty("track1.id"));
            track.setTrackname(environment.getProperty("track.name"));
            track.setTrackComments(environment.getProperty("track1.comment"));
            trackRepository.save(track);
        }
    }
