package com.app.karuna.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.karuna.dto.LoginDTO;
import com.app.karuna.entity.Campaign;
import com.app.karuna.entity.Donor;
import com.app.karuna.entity.Receiver;
import com.app.karuna.entity.Request;
import com.app.karuna.entity.Staff;
import com.app.karuna.repo.CampaignRepo;
import com.app.karuna.repo.DonorRepo;
import com.app.karuna.repo.ReceiverRepo;
import com.app.karuna.repo.RequestRepo;
import com.app.karuna.repo.StaffRepo;
import com.karuna.exception.ResourceNotFoundException;

@Service
@Transactional
public class CustomerExecutiveServiceImpl implements CustomerExecutiveService {

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
	private CampaignRepo campaignRepo;

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
	public List<Campaign> viewCampaigns() {

		return campaignRepo.findAll();
	}

	@Override
	public Campaign addCampaign(Campaign campaign) {

		return campaignRepo.save(campaign);
	}

	@Override
	public Campaign updateCampaign(Campaign campaign) {
		Optional<Campaign> campaignOptional = campaignRepo.findById(campaign.getId());
		if (campaignOptional.isPresent()) {
			Campaign existingCampaign = campaignOptional.get();
			existingCampaign.setName(campaign.getName());
			existingCampaign.setDescription(campaign.getDescription());

			Campaign updatedCampaign = campaignRepo.save(existingCampaign);
			updatedCampaign.setStatus(true);
			return updatedCampaign;
		}
		throw new ResourceNotFoundException("Campaign not found with ID: " + campaign.getId());
	}

	@Override
	public boolean deleteCampaign(Long campaignId) {

		campaignRepo.deleteById(campaignId);
		return true;
	}

}
