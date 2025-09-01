package com.back;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //무언가를 설정하는 클래스에 @Configuration를 붙히는 것이다.
public class AppConfig {

    @Bean
    public Parser parser(){
        return  Parser.builder().build();
    }

    @Bean
    public HtmlRenderer htmlRenderer(){
        return  HtmlRenderer.builder().build();
    }


}
