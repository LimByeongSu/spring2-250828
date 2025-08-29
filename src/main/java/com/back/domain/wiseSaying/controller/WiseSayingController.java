package com.back.domain.wiseSaying.controller;

import com.back.domain.wiseSaying.entity.WiseSaying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class WiseSayingController {

    int lastId=0;
    private List<WiseSaying> wiseSayingList = new ArrayList<>();

    @GetMapping("/wiseSaying/write")
    @ResponseBody
    public String write(        //  /wiseSaying/write? 뒤에 오는 값들을 받아 쓸수있음
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

    @GetMapping("/wiseSaying/list")
    @ResponseBody
    public String list() {

        String wiseSayings = wiseSayingList.stream()
                .map(w -> "<li>%s / %s / %s</li>".formatted(w.getId(), w.getContent(), w.getAuthor()))
                .collect(Collectors.joining("\n"));

        return """
                <ul>
                %s
                </ul>
                """.formatted(wiseSayings);
    }
    

    //트랜드를 보면
    //식별자(id같은)는 /wiseSaying/delete/1
    //부가정보는 /wiseSaying/delete?author="a"
    //처럼 하는데 정답은 없으니 그냥 편한거 쓰면 된다.

    @GetMapping("/wiseSaying/delete/{id}")  //식별자니까 /wiseSaying/delete/1의 형태로 받을것이다.
    @ResponseBody
    public String delete(
            @PathVariable int id    // @PathVariable은 path에 있는 값을 꺼내 쓰겠다는 의미다.
    ){
        Optional<WiseSaying> wiseSaying=wiseSayingList.stream()
                .filter(w -> w.getId() ==id)
                .findFirst();


        if(wiseSaying.isEmpty()){
            throw new RuntimeException(id+"번 명언은 존재하지 않습니다.");
        }

        wiseSayingList.remove(wiseSaying.get());    //id와 index는 다르기때문에 get()으로 객체를 기반으로 삭제

        return id+"번 명언이 삭제 되었습니다.";
    }
}
