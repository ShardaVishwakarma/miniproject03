package com.rani.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rani.request.SearchRequest;
import com.rani.response.SearchResponse;

@Service
public interface ReportService {

	public List<String> getPlanName();
	public List<String> getPlanStatus();
	public List<SearchResponse> searchPlans(SearchRequest request);
}
