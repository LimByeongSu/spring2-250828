package com.back.global.initData;

import com.back.domain.member.entity.Member;
import com.back.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    @Autowired
    @Lazy
    private BaseInitData self;
    private final MemberService memberService;

    @Bean
    ApplicationRunner initDataRunner() {
        return args -> {
            self.work1();
            self.work2();
        };

    }

    //@Transactional -> JpaRepository에 트랜잭션이 있음
    void work1() {

        if(memberService.count() > 0) {
            return;
        }

        Member member1 = memberService.join("systemUser", "시스템");
        Member member2 = memberService.join("adminUser", "관리자");
        Member member3 = memberService.join("user1", "유저1");
        Member member4 = memberService.join("user2", "유저2");
        Member member5 = memberService.join("user3", "유저3");
    }

    @Transactional  //
    void work2() {
        Member member4 = memberService.findByUsername("user2").get();
        member4.setNickname("new user2"); //원래는 객체 레벨에서 값만 바꾸면 DB에 반영이 안되지만 @Transactional이 붙으면 더티체킹의 대상이되어 DB에 변경내용이 반영됨
    }
}