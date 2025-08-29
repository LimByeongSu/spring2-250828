package com.back.domain.wiseSaying.controller;

import com.back.domain.wiseSaying.entity.WiseSaying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WiseSayingController {

    int lastId=0;
    private List<WiseSaying> wiseSayingList = new ArrayList<>();

    @GetMapping("/wiseSaying/write")
    @ResponseBody
    public String write(
            String content,
            String author
    ){

        if(content==null||content.trim().length()==0){ //입력 값이 null이거나 비어있거나 하는 경우
            throw new RuntimeException("명언을 입력해주세요.");
        }
        if(author==null||author.trim().length()==0){ //입력 값이 null이거나 비어있거나 하는 경우
            throw new RuntimeException("작가를 입력해주세요.");
        }
        WiseSaying wiseSaying=new WiseSaying(++lastId, content, author);
        wiseSayingList.add(wiseSaying);

        return wiseSaying.getId()+"번 명언이 등록되었습니다.";
    }

    /*@GetMapping("/wiseSaying/list")
    @ResponseBody
    public String list(){
        //return wiseSayingList;  //스프링에서 객체를 리턴하면 json형태로 나온다. 물론 보기에 좋지않으니 추천하지않는다.


    }*/
}
