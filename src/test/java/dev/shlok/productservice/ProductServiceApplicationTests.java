package dev.shlok.productservice;

import dev.shlok.productservice.inheritenceways.joinedtable.JTMentorRepository;
import dev.shlok.productservice.inheritenceways.joinedtable.JTUserRepository;
import dev.shlok.productservice.inheritenceways.joinedtable.User;
import dev.shlok.productservice.inheritenceways.mappedsuperclass.MSMentorRepository;
import dev.shlok.productservice.inheritenceways.mappedsuperclass.Mentor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceApplicationTests {
    @Autowired
    private MSMentorRepository msMentorRepository;
    @Autowired
    private JTMentorRepository jtMentorRepository;
    @Autowired
    private JTUserRepository jtUserRepository;

    @Test
    void contextLoads() {
    }

//    @Test
//    void testInheritenceMSC(){
//        Mentor mentor = new Mentor();
//        mentor.setEmail("shlok@scaler.com");
//        mentor.setPassword("pass");
//        mentor.setNumberOfMentees(10);
//        mentor.setNumberOfSessions(20);
//        msMentorRepository.save(mentor);
//    }

//    @Test
//    void testInheritanceJT(){
//        User user = new User();
//        user.setEmail("sh@sc.com");
//        user.setPassword("pwd");
//        jtUserRepository.save(user);
//
//        dev.shlok.productservice.inheritenceways.joinedtable.Mentor mentor = new dev.shlok.productservice.inheritenceways.joinedtable.Mentor();
//        mentor.setNumberOfMentees(3);
//        mentor.setNumberOfSessions(6);
//        jtMentorRepository.save(mentor);
//    }

}
