package org.petdians.user.member;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.petdians.user.member.entity.Member;
import org.petdians.user.member.repository.MemberRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testFindID(){
        Optional<Member> member = memberRepository.findById("mk");

        log.info( member.get() );

    }
}
