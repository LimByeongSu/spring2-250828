package com.back;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {


    //run()메서드가 자동으로 호출되게 되어있음
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("어플리케이션이 시작되었습니다.");
    }
}
