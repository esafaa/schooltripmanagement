package com.app.schooltripmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.schooltripmanagement.model.Approval;
import com.app.schooltripmanagement.model.Trip;
import com.app.schooltripmanagement.serviceImpl.ApprovalServiceImpl;
import com.app.schooltripmanagement.serviceImpl.TripServiceImpl;

/* Controller handling HTTP requests related to trip approvals for each trip
 * deleteApproval, approveTrip , checkout */

@Controller
public class ApprovalController {

	@Autowired
	ApprovalServiceImpl approvalService;

	@Autowired
	TripServiceImpl tripService;

	@GetMapping("/deleteApproval/{approvalId}/{tripId}")
	public String deleteApproval(@PathVariable(name = "approvalId") Long approvalId,
			@PathVariable(name = "tripId") Long tripId) {
		try {
			// Delete the approval with the given approvalId

			approvalService.deleteApproval(approvalId);
			return "redirect:/trips";
			// return "redirect:/viewApprovals/" + tripId;
		} catch (Exception e) {
			// Handle exceptions by redirecting to the list of trips

			return "redirect:/trips";
		}
	}

	@GetMapping("/approveTrip/{id}")
	public String approveTrip(@PathVariable Long id, Model model) {
		try {
			// Approve a trip and redirect the parent to the checkout page.
			Approval approval = tripService.approveTrip(id);
			if (approval != null) {
				// Here we will move them to parents their own approval list later
				return "redirect:/myApprovals";
			} else {
				// If already approved, show the checkout page
				model.addAttribute("alreadyApproved", true);
				Trip trip = tripService.getTripById(id).get();
				model.addAttribute("trip", trip);
				return "checkout";
			}
		} catch (Exception e) {
			// Handle exceptions by redirecting to the list of trips
			return "redirect:/trips";
		}
	}

	@GetMapping("/checkout/{tripId}")
	public String checkout(@PathVariable(name = "tripId") Long tripId, Model model) {
		// Prepare model attributes for the checkout page
		model.addAttribute("alreadyApproved", false);
		Trip trip = tripService.getTripById(tripId).get();
		if (trip == null) {
			// Redirect to the list of trips if the trip is not found
			return "redirect:/trips";
		}
		model.addAttribute("trip", trip);
		return "checkout";

	}
}
