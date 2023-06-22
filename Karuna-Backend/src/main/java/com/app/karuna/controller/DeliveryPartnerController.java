package com.app.karuna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.karuna.dto.LoginDTO;
import com.app.karuna.entity.Donor;
import com.app.karuna.entity.Location;
import com.app.karuna.entity.Receiver;
import com.app.karuna.entity.Request;
import com.app.karuna.entity.Staff;
import com.app.karuna.service.DeliveryPartnerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/delivery_partner")
public class DeliveryPartnerController {

    @Autowired
    private DeliveryPartnerService deliveryPartnerService;

    @PostMapping("/login")
    public ResponseEntity<Staff> loginStaff(@RequestBody LoginDTO loginDTO) {
        Staff staff = deliveryPartnerService.loginStaff(loginDTO);
        return ResponseEntity.ok(staff);
    }

    @PostMapping("/logout/{staffId}")
    public ResponseEntity<?> logoutStaff(@PathVariable Long staffId) {
        boolean loggedOut = deliveryPartnerService.logoutStaff(staffId);
        if (loggedOut) {
            return ResponseEntity.ok("Logged out successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }

    @GetMapping("/requests")
    public ResponseEntity<?> viewRequests() {
        List<Request> requests = deliveryPartnerService.viewRequests();
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/handleRequest/{staffId}")
    public ResponseEntity<?> handleRequest(@PathVariable Long staffId,@RequestBody Request request) {
        boolean handled = deliveryPartnerService.handleRequest(staffId,request);
        if (handled) {
            return ResponseEntity.ok("Request handled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request handling failed");
        }
    }

    @GetMapping("/donors")
    public ResponseEntity<?> viewDonors() {
        List<Donor> donors = deliveryPartnerService.viewDonors();
        return ResponseEntity.ok(donors);
    }

    @GetMapping("/receivers")
    public ResponseEntity<?> viewReceivers() {
        List<Receiver> receivers = deliveryPartnerService.viewReceivers();
        return ResponseEntity.ok(receivers);
    }

    @GetMapping("/checkPaymentStatus/{paymentId}")
    public ResponseEntity<?> checkPaymentStatus(@PathVariable Long paymentId) {
        boolean paymentStatus = deliveryPartnerService.checkPaymentStatus(paymentId);
        if (paymentStatus) {
            return ResponseEntity.ok("Payment is completed");
        } else {
            return ResponseEntity.ok("Payment is pending");
        }
    }

    @GetMapping("/viewDonorLocation/{donorId}")
    public ResponseEntity<Location> viewDonorLocation(@PathVariable Long donorId) {
        Location donorLocation = deliveryPartnerService.viewDonorLocation(donorId);
        return ResponseEntity.ok(donorLocation);
    }

    @GetMapping("/viewReceiverLocation/{receiverId}")
    public ResponseEntity<Location> viewReceiverLocation(@PathVariable Long receiverId) {
        Location receiverLocation = deliveryPartnerService.viewReceiverLocation(receiverId);
        return ResponseEntity.ok(receiverLocation);
    }

    @PostMapping("/confirmDelivery/{deliveryId}")
    public ResponseEntity<?> confirmDelivery(@PathVariable Long deliveryId) {
        boolean confirmed = deliveryPartnerService.confirmDelivery(deliveryId);
        if (confirmed) {
            return ResponseEntity.ok("Delivery confirmed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delivery confirmation failed");
        }
    }
}
