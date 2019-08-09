package com.stackroute.springboottask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.springboottask.domain.Track;
import com.stackroute.springboottask.exceptions.GlobalExceptionHandler;
import com.stackroute.springboottask.exceptions.TrackAlreadyExistException;
import com.stackroute.springboottask.exceptions.TrackNotFoundException;
import com.stackroute.springboottask.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.graalvm.compiler.graph.Graph.SourcePositionTracking.Track;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private com.stackroute.springboottask.domain.Track track;

    @MockBean
    private TrackService muzixService;

    @InjectMocks
    private TrackController muzixController;

    private List<Track> list = null;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(muzixController).setControllerAdvice(GlobalExceptionHandler.class).build();
        track = new Track();
        track.setTrackId("1");
        track.setTrackname("Album1");
        track.setTrackComments("old songs");
        list = new ArrayList<>();
        list.add(track);
    }

    @After
    public void tearDown(){

        track=null;
    }
    @Test
    public void saveTrack() throws TrackAlreadyExistException,Exception
    {
        when(muzixService.saveTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        verify(muzixService,times(1)).saveTrack(track);
    }
    @Test
    public void saveTrackFailure() throws TrackAlreadyExistException,Exception {
        when(muzixService.saveTrack(any())).thenThrow(TrackAlreadyExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        verify(muzixService,times(1)).saveTrack(track);
    }
    @Test
    public void getAllTracks() throws Exception {
        when(muzixService.getAllTracks()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        verify(muzixService,times(1)).getAllTracks();
    }

    @Test
    public void getTrackAfterUpdatingComments() throws TrackNotFoundException,Exception {
        when(muzixService.updateTrackComments(track.getTrackId(),track.getTrackComments())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/id/comment")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        verify(muzixService,times(1)).updateTrackComments((String)any(),(String)any());
    }
    @Test
    public void getTrackAfterUpdatingCommentsFailure() throws TrackNotFoundException,Exception {
        when(muzixService.updateTrackComments(any(),any())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/id/comment")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        verify(muzixService,times(1)).updateTrackComments((String)any(),(String)any());
    }

    @Test
    public void getTrackAfterDeleting() throws TrackNotFoundException,Exception{
        when(muzixService.deleteTrack(track.getTrackId())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/id")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        verify(muzixService,times(1)).deleteTrack((String)any());
    }
    @Test
    public void getTrackAfterDeletingFailure() throws TrackNotFoundException,Exception{
        when(muzixService.deleteTrack(any())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/id")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        verify(muzixService,times(1)).deleteTrack((String)any());
    }

   /* @Test
    public void getTrackByName() throws TrackNotFoundException,Exception{
        when(muzixService.findTrackByName(track.getTrackName())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/name")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        verify(muzixService,times(1)).findTrackByName((String)any());
    }

   @Test
    public void getTrackByNameFailure() throws TrackNotFoundException,Exception{
        when(muzixService.findTrackByName(any())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/name")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
       verify(muzixService,times(1)).findTrackByName((String)any());
    }*/


    private static String asJsonString(final Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);

        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}