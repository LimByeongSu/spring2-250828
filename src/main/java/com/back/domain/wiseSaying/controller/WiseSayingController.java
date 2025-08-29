package com.back.domain.wiseSaying.controller;

import com.back.domain.wiseSaying.entity.WiseSaying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class WiseSayingController {

    int lastId=0;
    private List<WiseSaying> wiseSayingList = new ArrayList<>() {{
        add(new WiseSaying(1, "명언1", "작가1"));
        add(new WiseSaying(2, "명언2", "작가2"));
        add(new WiseSaying(3, "명언3", "작가3"));
        add(new WiseSaying(4, "명언4", "작가4"));
        add(new WiseSaying(5, "명언5", "작가5"));
    }};

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

    @GetMapping("/wiseSayings")
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
        //삭제할 대상 찾기
        WiseSaying wiseSaying = findById(id);

        wiseSayingList.remove(wiseSaying);    //id와 index는 다르기때문에 객체를 기반으로 삭제

        return id+"번 명언이 삭제 되었습니다.";
    }

    @GetMapping("/wiseSaying/modify/{id}")
    // wiseSaying/modify/{id}?content=""&author="" 형태일 것이다.
    @ResponseBody
    public String modify(
            @PathVariable int id,
            @RequestParam(defaultValue = "기본값") String author,
            @RequestParam(defaultValue = "기본값") String content
    ){
        //수정할 대상 찾기
        WiseSaying wiseSaying = findById(id);
        wiseSaying.setAuthor(author);
        wiseSaying.setContent(content);

        return id+"번 명언이 수정되었습니다.";
    }

    //상세보기 기능
    @GetMapping("/wiseSayings/{id}")
    @ResponseBody
    public String detail(
            @PathVariable int id
    ){
        WiseSaying wiseSaying = findById(id);



        return """
                <h1>번호 : %s</h1>
                <div>명언 : %s</div>
                <div>작가 : %s</div>
                """.formatted(wiseSaying.getId(), wiseSaying.getContent(), wiseSaying.getAuthor());
    }

    private WiseSaying findById(int id) {
        Optional<WiseSaying> wiseSaying=wiseSayingList.stream()
                .filter(w -> w.getId() == id)
                .findFirst();


        if(wiseSaying.isEmpty()){
            throw new RuntimeException(id +"번 명언은 존재하지 않습니다.");
        }
        return wiseSaying.get();
    }


}
