package com.casestudy.digi_claim.repository;

import com.casestudy.digi_claim.entity.ClaimForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimFormRepository extends JpaRepository<ClaimForm, Integer> {
}
