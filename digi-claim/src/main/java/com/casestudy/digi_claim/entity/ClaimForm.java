package com.casestudy.digi_claim.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="claim_form")
public class ClaimForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int claimId;
    private String policyNumber;
    private int totalExpense;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date submissionDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "userId")
    private UserInfo userInfo;
    @OneToMany(mappedBy = "claimForm", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Document> documents;

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
        for(Document doc:documents){
            doc.setClaimForm(this);
        }
    }
}
