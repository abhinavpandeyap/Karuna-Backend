package com.app.karuna.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.karuna.dto.LoginDTO;
import com.app.karuna.entity.Staff;
import com.app.karuna.repo.StaffRepo;
import com.karuna.exception.ResourceNotFoundException;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private StaffRepo staffRepo;

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Staff loginStaff(LoginDTO loginDTO) {
		Staff staff = staffRepo.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
		if (staff == null) {
			throw new ResourceNotFoundException("Bad Credentials !!!!!");
		}
		staff.setStatus(true);
		return mapper.map(staff, Staff.class);
	}

	@Override
	public boolean logoutStaff(Long staffId) {
		Optional<Staff> staffOptional = staffRepo.findById(staffId);
		if (staffOptional.isPresent()) {
			Staff staff = staffOptional.get();
			staff.setStatus(false);
			staffRepo.save(staff);
			return true;
		}
		return false;

	}


	@Override
	public Staff addStaff(Staff staff) {
		return staffRepo.save(staff);
	}

	@Override
	public Staff updateStaff(Staff staff) {
		Optional<Staff> staffOptional = staffRepo.findById(staff.getId());
	    if (staffOptional.isPresent()) {
	        Staff existingStaff = staffOptional.get();
	        existingStaff.setName(staff.getName());
	        existingStaff.setEmail(staff.getEmail());
	        existingStaff.setPassword(staff.getPassword());
	        existingStaff.setPhone(staff.getPhone());
	        existingStaff.setAddress(staff.getAddress());
	        existingStaff.setLocation(staff.getLocation());

	        Staff updatedStaff = staffRepo.save(existingStaff);
	        updatedStaff.setStatus(true);
	        return updatedStaff;
	    }
	   
	    throw new ResourceNotFoundException("Staff not found with ID: " + staff.getId());
	
	}

	@Override
	public boolean deleteStaff(Long staffId) {
		staffRepo.deleteById(staffId);
		return true;
	}

	@Override
	public List<Staff> viewStaffs() {
		
		return staffRepo.findAll();
	}

}
