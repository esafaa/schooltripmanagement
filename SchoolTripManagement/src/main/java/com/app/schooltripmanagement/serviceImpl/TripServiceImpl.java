package com.app.schooltripmanagement.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.schooltripmanagement.model.Approval;
import com.app.schooltripmanagement.model.Trip;
import com.app.schooltripmanagement.model.User;
import com.app.schooltripmanagement.repository.ApprovalRepository;
import com.app.schooltripmanagement.repository.TripRepository;
import com.app.schooltripmanagement.repository.UserRepository;
import com.app.schooltripmanagement.util.SpringSecurityUtil;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TripServiceImpl {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    // Service method to retrieve a list of all trips

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
    // Service method to save a trip

    public void saveTrip(Trip trip) {
        tripRepository.save(trip);
    }
    // Service method to update a trip

    public Trip updateTrip(Trip trip) {
        Optional<Trip> existingTrip = tripRepository.findByIdNative(trip.getId());
        if (existingTrip.isPresent()) {

            trip.setApproval(existingTrip.get().getApproval());
            return tripRepository.save(trip);
        } else {
            throw new RuntimeException("Trip not found");
        }
    }
    // Service method to retrieve a trip by its ID

    public Optional<Trip> getTripById(Long id) {
        Optional<Trip> trip = tripRepository.findByIdNative(id);
        if (trip.isPresent()) {
            return trip;
        } else {
            throw new RuntimeException("Trip not found");
        }

    }
    // Service method to approve a trip

    public Approval approveTrip(Long id) {
        try {
            Optional<Trip> trip = tripRepository.findByIdNative(id);
            if (trip.isPresent()) {
                String username = SpringSecurityUtil.getCurrentUsername();
                Optional<User> user = userRepository.findUserByUsername(username);
                if (user.isPresent()) {
                    if (!trip.get().getApproval().isEmpty()) {
                        List<Approval> approvals = trip.get().getApproval();

                        for (Approval appr : approvals
                        ) {
                            if (appr.getUser().equals(user.get())) {
                                log.info("You have already approved this trip before");
                                return null;
                            }
                        }
                    }
                    Approval approval = new Approval();
                    approval.setStatus(true);
                    approval.setUser(user.get());
                    approval.setTrip(trip.get());
                    Approval savedApproval = approvalRepository.save(approval);
                    return savedApproval;
                }
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }
    
    // Service method to delete a trip

    public void deleteTrip(Trip trip) {
        tripRepository.delete(trip);;
    }
}
