package com.back;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //무언가를 설정하는 클래스에 @Configuration를 붙히는 것이다.
public class AppConfig {

    @Bean
    public PersonRepository personRepository() {
        System.out.println("version 1 select");
        return new PersonRepository(1);
    }

    @Bean
    public PersonRepository personRepositoryV2() {
        System.out.println("version 2 select");
        return new PersonRepository(2);
    }


    //JPA에서 쓰던 내용이고 자동으로 실행되는 이유는 빈 방식이기 때문이다.
    //ApplicationRunner는 인터페이스라 new하는 방식이 안된다.
    /*public ApplicationRunner myApplicationRunner() {
        return args -> {
            PersonRepository personRepository = personRepository();
        };
    }*/
}
