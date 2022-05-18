package com.rani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rani.entity.EligibilityDtlsEntity;

@Repository
public interface EligDtlsRepository extends JpaRepository<EligibilityDtlsEntity, Integer> {

	@Query("Select distinct(planName) from EligDtlsEntity")
	public List<String> getUniquePlanNames();
	
	@Query("Select distinct(planStatus) from EligDtlsEntity")
	public List<String> getUniquePlanStatus();
	
	
}
