package com.app.karuna.service;

import java.util.List;

import com.app.karuna.dto.LoginDTO;
import com.app.karuna.entity.Staff;

public interface AdminService {
	
	Staff loginStaff(LoginDTO loginDTO);

	boolean logoutStaff(Long staffId);
	
	Staff addStaff(Staff staff);
	
	Staff updateStaff(Staff staff);
	
	boolean deleteStaff(Long staffId);
	
	
	List<Staff> viewStaffs();
	
	

}
