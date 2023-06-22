package com.app.karuna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.karuna.dto.LoginDTO;
import com.app.karuna.entity.Campaign;
import com.app.karuna.entity.Donor;
import com.app.karuna.entity.Receiver;
import com.app.karuna.entity.Request;
import com.app.karuna.entity.Staff;
import com.app.karuna.service.CustomerExecutiveService;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer_executive")
public class CustomerExecutiveController {

    @Autowired
    private CustomerExecutiveService customerExecutiveService;

    @PostMapping("/login")
    public ResponseEntity<Staff> loginStaff(@RequestBody LoginDTO loginDTO) {
        Staff staff = customerExecutiveService.loginStaff(loginDTO);
        return ResponseEntity.ok(staff);
    }

    @PostMapping("/logout/{staffId}")
    public ResponseEntity<?> logoutStaff(@PathVariable Long staffId) {
        boolean loggedOut = customerExecutiveService.logoutStaff(staffId);
        if (loggedOut) {
            return ResponseEntity.ok("Logged out successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }

    @GetMapping("/requests")
    public ResponseEntity<?> viewRequests() {
        List<Request> requests = customerExecutiveService.viewRequests();
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/handleRequest/{staffId}")
    public ResponseEntity<?> handleRequest(@PathVariable Long staffId,@RequestBody Request request) {
        boolean handled = customerExecutiveService.handleRequest(staffId,request);
        if (handled) {
            return ResponseEntity.ok("Request handled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request handling failed");
        }
    }

    @GetMapping("/donors")
    public ResponseEntity<?> viewDonors() {
        List<Donor> donors = customerExecutiveService.viewDonors();
        return ResponseEntity.ok(donors);
    }

    @GetMapping("/receivers")
    public ResponseEntity<?> viewReceivers() {
        List<Receiver> receivers = customerExecutiveService.viewReceivers();
        return ResponseEntity.ok(receivers);
    }

    @GetMapping("/campaigns")
    public ResponseEntity<?> viewCampaigns() {
        List<Campaign> campaigns = customerExecutiveService.viewCampaigns();
        return ResponseEntity.ok(campaigns);
    }

    @PostMapping("/addCampaign")
    public ResponseEntity<Campaign> addCampaign(@RequestBody Campaign campaign) {
        Campaign addedCampaign = customerExecutiveService.addCampaign(campaign);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCampaign);
    }

    @PutMapping("/updateCampaign")
    public ResponseEntity<Campaign> updateCampaign(@RequestBody Campaign campaign) {
        Campaign updatedCampaign = customerExecutiveService.updateCampaign(campaign);
        return ResponseEntity.ok(updatedCampaign);
    }

    @DeleteMapping("/deleteCampaign/{campaignId}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Long campaignId) {
        boolean deleted = customerExecutiveService.deleteCampaign(campaignId);
        if (deleted) {
            return ResponseEntity.ok("Campaign deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Campaign deletion failed");
        }
    }
}
