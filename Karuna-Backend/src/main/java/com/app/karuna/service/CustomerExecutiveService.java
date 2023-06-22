package com.app.karuna.service;

import java.util.List;

import com.app.karuna.dto.LoginDTO;
import com.app.karuna.entity.Campaign;
import com.app.karuna.entity.Donor;
import com.app.karuna.entity.Receiver;
import com.app.karuna.entity.Request;
import com.app.karuna.entity.Staff;

public interface CustomerExecutiveService {

	public Staff loginStaff(LoginDTO loginDTO);

	public boolean logoutStaff(Long staffId);
	
	List<Request> viewRequests();

	boolean handleRequest(Long staffId,Request request);
	
	List<Donor> viewDonors();
	
	List<Receiver> viewReceivers();
	
	List<Campaign> viewCampaigns();
	
	Campaign addCampaign(Campaign campaign);
	
	Campaign updateCampaign(Campaign campaign);
	
	boolean deleteCampaign(Long campaignId);
	
	
	

}
