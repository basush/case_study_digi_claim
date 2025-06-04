package com.casestudy.digital_claim;

import com.casestury.auth_service.entity.UserInfo;
import com.casestury.auth_service.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
public class DigitalClaimAppJpaTest {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Test
    public void createUserTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("ravi@gmail.com");
        userInfo.setName("Ravi");
        userInfo.setPassword("1234");
        userInfo.setRoles("ROLE_USER");

        userInfo  = userInfoRepository.save(userInfo);

        assertTrue(userInfo.getUserId()==1, "Values are correct");



    }
}
