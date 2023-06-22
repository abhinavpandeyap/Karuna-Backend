package com.app.karuna.service;

import java.util.List;

import com.app.karuna.dto.LoginDTO;
import com.app.karuna.entity.Donor;
import com.app.karuna.entity.Location;
import com.app.karuna.entity.Receiver;
import com.app.karuna.entity.Request;
import com.app.karuna.entity.Staff;

public interface DeliveryPartnerService {

	Staff loginStaff(LoginDTO loginDTO);

	boolean logoutStaff(Long staffId);

	List<Request> viewRequests();
	
	boolean handleRequest(Long staffId,Request request);

	List<Donor> viewDonors();
	
	List<Receiver> viewReceivers();
	
	boolean checkPaymentStatus(Long paymentId);
	
	Location viewDonorLocation(Long donorId);
	
	Location viewReceiverLocation(Long receiverId);
	
	boolean confirmDelivery(Long deliveryId);
}
