package com.jinnie.guestbook.repository;

import com.jinnie.guestbook.entity.Mmember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MmemberRepositoryTests {

    @Autowired
    private MmemberRepository mmemberRepository;

    @Test
    public void insertMembers(){
        IntStream.rangeClosed(1,100).forEach(i ->{
            Mmember mmember = Mmember.builder()
                    .email("r"+i+"@test.com")
                    .pw("1111")
                    .nickname("reviewer"+i).build();
            mmemberRepository.save(mmember);
        });
    }
}
