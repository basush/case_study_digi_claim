package com.casestudy.digi_claim.kafka;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimFormKafkaDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -2755737997681305041L;
    @NotNull
    @Size(min = 2, max = 20)
    private String policyNumber;
    @Max(20000)
    @Min(1000)
    private int totalExpense;
    private int userId;
}
