package com.app.karuna.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.karuna.dto.LoginDTO;
import com.app.karuna.entity.Donor;
import com.app.karuna.entity.Location;
import com.app.karuna.entity.Receiver;
import com.app.karuna.entity.Request;
import com.app.karuna.entity.Staff;
import com.app.karuna.repo.DeliveryRepo;
import com.app.karuna.repo.DonorRepo;
import com.app.karuna.repo.PaymentRepo;
import com.app.karuna.repo.ReceiverRepo;
import com.app.karuna.repo.RequestRepo;
import com.app.karuna.repo.StaffRepo;
import com.karuna.exception.ResourceNotFoundException;

@Service
@Transactional
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

	@Autowired
	private StaffRepo staffRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RequestRepo requestRepo;

	@Autowired
	private ReceiverRepo receiverRepo;

	@Autowired
	private DonorRepo donorRepo;
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	@Autowired
	private DeliveryRepo deliveryRepo;

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
	public List<Request> viewRequests() {
		
		return requestRepo.findAllByStatusFalse();
	}

	@Override
	public boolean handleRequest(Long staffId, Request request) {
		Request obtainedRequest = requestRepo.getReferenceById(request.getId());
		obtainedRequest.setStatus(true);
		obtainedRequest.setStaff(staffRepo.getReferenceById(staffId));
		obtainedRequest.setHandledDateTime(LocalDateTime.now());

		return true;
	}


	@Override
	public List<Donor> viewDonors() {
		return donorRepo.findAll();
	}

	@Override
	public List<Receiver> viewReceivers() {
		return receiverRepo.findAll();
	}

	@Override
	public boolean checkPaymentStatus(Long paymentId) {
		if(paymentRepo.findById(paymentId)!=null)
		return true;
		return false;
	}

	@Override
	public Location viewDonorLocation(Long donorId) {
	  return donorRepo.getReferenceById(donorId).getLocation();
	}

	@Override
	public Location viewReceiverLocation(Long receiverId) {
		return receiverRepo.getReferenceById(receiverId).getLocation();
	}

	@Override
	public boolean confirmDelivery(Long deliveryId) {
		deliveryRepo.getReferenceById(deliveryId).setStatus(true);
		return true;
	}


}
