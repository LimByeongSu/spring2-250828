package com.back;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor  //생성자 주입
public class PersonController {

    private final PersonService personService;


    @GetMapping("/people")
    @ResponseBody
    public String people(){

        return "사람 수 : "+personService.count();
    }
}
