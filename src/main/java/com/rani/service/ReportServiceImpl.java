package com.rani.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.rani.entity.EligibilityDtlsEntity;
import com.rani.repository.EligDtlsRepository;
import com.rani.request.SearchRequest;
import com.rani.response.SearchResponse;

//@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private EligDtlsRepository repo;

	@Override
	public List<String> getPlanName() {

		return repo.getUniquePlanNames();
	}

	@Override
	public List<String> getPlanStatus() {

		return repo.getUniquePlanStatus();
	}

	@Override
	public List<SearchResponse> searchPlans(SearchRequest request) {
		List<EligibilityDtlsEntity> eligeRecords = null;

		if (isSearchReqEmpty(request)) {
			eligeRecords = repo.findAll();
		} else {
			// user can select only plan-name and click on search button
			String planName = request.getPlanName();
			String planStatus = request.getPlanStatus();
			LocalDate startDate = request.getStartDate();
			LocalDate endDate = request.getEndDate();

			EligibilityDtlsEntity entity = new EligibilityDtlsEntity();
			if (planName != null && planName.equals("")) {
				entity.setPlanName(planName);
			}
			if (planStatus != null && planStatus.equals("")) {
				entity.setPlanStatus(planStatus);

			}
			if (startDate != null && endDate != null) {
				entity.setStartDate(startDate);
				entity.setEndDate(endDate);
			}

			Example<EligibilityDtlsEntity> of = Example.of(entity);
			List<EligibilityDtlsEntity> findAall = repo.findAll(of);

		}
		List<SearchResponse> response = new ArrayList<>();
		for (EligibilityDtlsEntity eligeRecord : eligeRecords) {
			SearchResponse sr = new SearchResponse();
			BeanUtils.copyProperties(eligeRecord, sr);
			response.add(sr);
		}

		return response;
	}

	public boolean isSearchReqEmpty(SearchRequest request) {

		boolean isEmpty = true;

		if (request.getPlanName() != null && request.getPlanName().equals("")) {

			isEmpty = false;
		}
		if (request.getPlanStatus() != null && request.getPlanStatus().equals("")) {

			isEmpty = false;
		}
		if (request.getStartDate() != null && request.getStartDate().equals("")) {

			isEmpty = false;
		}
		if (request.getEndDate() != null && request.getEndDate().equals("")) {

			isEmpty = false;
		}

		return isEmpty;

	}

}
