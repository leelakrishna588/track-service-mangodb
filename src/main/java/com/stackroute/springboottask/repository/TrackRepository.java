package com.stackroute.springboottask.repository;

import com.stackroute.springboottask.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackRepository extends MongoRepository<Track,String> {
}
