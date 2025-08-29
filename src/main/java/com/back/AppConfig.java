package com.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration  //무언가를 설정하는 클래스에 @Configuration를 붙히는 것이다.
public class AppConfig {

    @Autowired
    @Lazy
    private AppConfig self; //AppConfig이므로 this와 같다고 생각할수있지만 this와는 @Lazy의 차이가 있다.



    //익명 객체로 만드는 방식 myApplicationRunner1,2는 따로 클래스를 만들어서 구현하고 그걸 받는 방식
    @Bean
    public ApplicationRunner myApplicationRunner() {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                work1();
                work2();
            }
        };
    }

    @Transactional  //this.work()처럼 this로 들어오는 경우는 트랜잭션이 적용되지 않는다.
    // 그래서 그냥 work1()을 호출하면 this.work1()이니 self를 만들고 self.work1()으로 호출해야한다.
    private void work1() {
        System.out.println("work1");
    }

    @Transactional
    private void work2() {
        System.out.println("work2");
    }




}
