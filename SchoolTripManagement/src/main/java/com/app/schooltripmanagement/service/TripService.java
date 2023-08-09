package com.app.schooltripmanagement.service;

import java.util.List;
import java.util.Optional;

import com.app.schooltripmanagement.model.Approval;
import com.app.schooltripmanagement.model.Trip;

public interface TripService {
	 public List<Trip> getAllTrips();
	 public void saveTrip(Trip trip);
	 public Trip updateTrip(Trip trip);
	 public Optional<Trip> getTripById(Long id);
	 public Approval approveTrip(Long id);
	 public void deleteTrip(Trip trip);
}
