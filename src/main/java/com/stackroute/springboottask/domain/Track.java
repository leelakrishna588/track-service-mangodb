package com.stackroute.springboottask.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Track {

    @Id
    private String trackId;
    private String trackname;
    private String trackComments;

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackname() {
        return trackname;
    }

    public void setTrackname(String trackname) {
        this.trackname = trackname;
    }

    public String getTrackComments() {
        return trackComments;
    }

    public void setTrackComments(String trackComments) {
        this.trackComments = trackComments;
    }

    public Track() {
    }


    public Track(String trackId, String trackname, String trackComments) {
        this.trackId = trackId;
        this.trackname = trackname;
        this.trackComments = trackComments;
    }




}
