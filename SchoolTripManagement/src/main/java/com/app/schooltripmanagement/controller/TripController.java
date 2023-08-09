package com.app.schooltripmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.schooltripmanagement.model.File;
import com.app.schooltripmanagement.model.Trip;
import com.app.schooltripmanagement.serviceImpl.TripServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Controller to handle http request related to managing trips. 
 * Display existing trips, files related to a trip, creating a field trip, 
 * deletion and updating the field trip
 */
@Controller
@Slf4j
public class TripController {

	@Autowired
	TripServiceImpl tripService;

	// Controller for displaying the list of trips
	@GetMapping("/trips")
	public String showTripList(Model model) {
		List<Trip> trips = tripService.getAllTrips();
		model.addAttribute("trips", trips);
		return "trip_list"; // Return the name of the "trip_list" view template
	}

	// Controller for displaying trip files associated with a trip

	@GetMapping("/tripFiles/{id}")
	public String showTripFiles(@PathVariable Long id, Model model) {
		Trip trip = tripService.getTripById(id).get();
		if (trip == null) {
			return "redirect:/trips";
		}
		model.addAttribute("trip", trip);
		return "trip_files";
	}

	// Controller for displaying the trip form for creating or editing trips

	@GetMapping("/tripForm")
	public String showTripForm(Model model) {
		model.addAttribute("trip", new Trip());
		return "trip_form";
	}

	// Controller for handling form submission to save a trip

	@PostMapping("/saveTrip")
	public String saveTrip(@ModelAttribute Trip trip, @RequestParam("files") MultipartFile[] files) {
		try {
			// Process the uploaded files
			List<File> tripFilesList = new ArrayList<>();
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					File tripImage = new File();
					tripImage.setFileData(file.getBytes());
					tripImage.setTrip(trip);
					tripImage.setFileType(file.getContentType());
					tripImage.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
					tripFilesList.add(tripImage);
				}
			}
			trip.setTripFiles(tripFilesList);
			if (trip.getId() != null) {
				tripService.updateTrip(trip);
			} else {
				tripService.saveTrip(trip);
			}
			return "redirect:/trips";// Redirect to the list of trips
		} catch (IOException e) {
			return "redirect:/tripForm?error";// Redirect to the trip form with an error message
		}
	}

	// Controller for displaying trip approvals
	@GetMapping("/viewApprovals/{id}")
	public String showTripApprovals(@PathVariable(name = "id") Long tripId, Model model) {
		Trip trip = tripService.getTripById(tripId).get();
		if (trip == null) {
			return "redirect:/trips";
		}
		model.addAttribute("trip", trip);
		return "trip_approvals"; // Return the name of the "trip_approvals" view template
	}

	// Controller for displaying the trip form for updating an existing trip
	@GetMapping("/updateTrip/{tripId}")
	public String showTripForm(@PathVariable(name = "tripId") Long tripId, Model model) {
		Trip trip = tripService.getTripById(tripId).get();
		if (trip == null) {
			return "redirect:/trips";
		}
		model.addAttribute("trip", trip);
		model.addAttribute("files", trip.getTripFiles());
		return "trip_form"; // Return the name of the "trip_form" view template
	}

	// Controller for handling deletion of a trip
	@GetMapping("/deleteTrip/{tripId}")
	public String deleteTripForm(@PathVariable(name = "tripId") Long tripId, Model model) {
		Trip trip = tripService.getTripById(tripId).get();

		if (trip.getId() != null) {
			tripService.deleteTrip(trip);
		} else {

		}
		return "redirect:/trips";
	}

}
