package com.casestudy.digi_claim.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimFormDto {
    @NotNull
    @Size(min = 2, max = 20)
    private String policyNumber;
    @Max(20000)
    @Min(1000)
    private int totalExpense;
    private int userId;
    List<MultipartFile> documents;
}
