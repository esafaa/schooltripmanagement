package com.app.schooltripmanagement.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.schooltripmanagement.model.Trip;
import com.app.schooltripmanagement.repository.TripRepository;
import com.app.schooltripmanagement.serviceImpl.TripServiceImpl;


@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripServiceImpl tripService;

    @Test
    public void testGetTripByIdWhenTripExists() {
        Long tripId = 2L;
        Trip trip = new Trip();

        when(tripRepository.findByIdNative(tripId)).thenReturn(Optional.of(trip));

        Optional<Trip> result = tripService.getTripById(tripId);

       
        assertEquals(trip, result.get());
    }

   
}
