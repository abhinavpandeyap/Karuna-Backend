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
import com.app.karuna.entity.Staff;
import com.app.karuna.service.AdminService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<Staff> loginStaff(@RequestBody LoginDTO loginDTO) {
        Staff staff = adminService.loginStaff(loginDTO);
        return ResponseEntity.ok(staff);
    }

    @PostMapping("/logout/{staffId}")
    public ResponseEntity<?> logoutStaff(@PathVariable Long staffId) {
        boolean loggedOut = adminService.logoutStaff(staffId);
        if (loggedOut) {
            return ResponseEntity.ok("Logged out successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }

    @PostMapping("/addStaff")
    public ResponseEntity<Staff> addStaff(@RequestBody Staff staff) {
        Staff addedStaff = adminService.addStaff(staff);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedStaff);
    }

    @PutMapping("/updateStaff")
    public ResponseEntity<Staff> updateStaff(@RequestBody Staff staff) {
        Staff updatedStaff = adminService.updateStaff(staff);
        return ResponseEntity.ok(updatedStaff);
    }

    @DeleteMapping("/deleteStaff/{staffId}")
    public ResponseEntity<?> deleteStaff(@PathVariable Long staffId) {
        boolean deleted = adminService.deleteStaff(staffId);
        if (deleted) {
            return ResponseEntity.ok("Staff deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Staff deletion failed");
        }
    }

    @GetMapping("/viewStaffs")
    public ResponseEntity<?> viewStaffs() {
        List<Staff> staffs = adminService.viewStaffs();
        return ResponseEntity.ok(staffs);
    }
}
