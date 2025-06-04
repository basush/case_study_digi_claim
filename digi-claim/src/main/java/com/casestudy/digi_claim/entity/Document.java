package com.casestudy.digi_claim.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int docId;
    String documentName;
    String docPath;
    @Lob
    byte[] docData;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="claim_id_fk",referencedColumnName = "claimId")
    ClaimForm claimForm;
}
