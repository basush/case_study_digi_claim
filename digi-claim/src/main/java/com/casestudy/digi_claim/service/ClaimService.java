package com.casestudy.digi_claim.service;

import com.casestudy.digi_claim.entity.Document;
import com.casestudy.digi_claim.kafka.ClaimEvent;
import com.casestudy.digi_claim.kafka.ClaimFormKafkaDto;
import com.casestudy.digi_claim.kafka.ClaimProducer;
import com.casestudy.digi_claim.repository.ClaimFormRepository;
import com.casestudy.digi_claim.repository.UserInfoRepository;
import com.casestudy.digi_claim.dto.ClaimFormDto;
import com.casestudy.digi_claim.entity.ClaimForm;
import com.casestudy.digi_claim.entity.UserInfo;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimService {
    @Autowired
    private ClaimFormRepository repository;
    @Autowired
    private ClaimProducer claimProducer;

    @Autowired
    UserInfoRepository userRepo;

    @Value("${filestore.path}")
    private String FILESTORE_PATH;

    @Transactional(rollbackOn = IOException.class)
    public String submitClaimForm(ClaimFormDto claimFormDto) throws IOException {
        ClaimForm entity = new ClaimForm();
        BeanUtils.copyProperties(claimFormDto,entity);
        entity.setSubmissionDate(new Date(System.currentTimeMillis()));
        UserInfo user = userRepo.findById(claimFormDto.getUserId()).get();
        entity.setUserInfo(user);

        List<MultipartFile> docs= claimFormDto.getDocuments();
        List<Document> docList = new ArrayList<>();
        int i=0;
        for(MultipartFile file:docs){
           Document doc = new Document();
           doc.setDocData(file.getBytes());
           doc.setCreatedDate(new Date(System.currentTimeMillis()));
           doc.setDocumentName(file.getOriginalFilename());
           doc.setClaimForm(entity);
           doc.setDocPath(FILESTORE_PATH);
           docList.add(doc);
        }
        entity.setDocuments(docList);

        repository.save(entity);

        fileUpload(docs);

        ClaimEvent event = new ClaimEvent();
        event.setMessage("Pending for Approval");
        event.setStatus("PENDING");
        ClaimFormKafkaDto dto = new ClaimFormKafkaDto();
        BeanUtils.copyProperties(claimFormDto,dto);
        event.setClaimFormKafkaDto(dto);

        claimProducer.sendMessage(event);

        return "Form submitted Successfully";

    }

    private void fileUpload(List<MultipartFile> docs) throws IOException {
//        if(true){
//            throw new FileNotFoundException();
//        }

        Path uploadPath = Paths.get(FILESTORE_PATH);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file to the server
        for(MultipartFile file:docs) {
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
        }
    }
}
