package com.casestudy.digi_claim.controller;

import com.casestudy.digi_claim.dto.ClaimFormDto;
import com.casestudy.digi_claim.service.ClaimService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/digi-claim")
public class ClaimController {
    @Autowired
    private ClaimService claimService;

    @PostMapping("/submit-form")
    public String submitClaimForm(@Valid @ModelAttribute ClaimFormDto claimForm) {
        System.out.println("submitClaimForm() called");
        String s;
        try {
            s=claimService.submitClaimForm(claimForm);
        } catch (IOException e) {
            System.out.println("Failed to Submit ->"+e.getMessage());
            s="Failed to Submit ->"+e.getMessage();
        }
        return s;
    }

    @GetMapping("/check-auth")
    public String checkAuth(){
        return "Auth Verified";
    }
}
