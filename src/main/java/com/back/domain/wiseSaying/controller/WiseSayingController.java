package com.back.domain.wiseSaying.controller;

import com.back.domain.standard.MarkdownService;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor    // 빈 주입
public class WiseSayingController {

    private final WiseSayingService wiseSayingService;
    private final MarkdownService markdownService;


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
        WiseSaying wiseSaying = wiseSayingService.write(content, author);


        return wiseSaying.getId()+"번 명언이 등록되었습니다.";
    }

    @GetMapping("/wiseSayings")
    @ResponseBody
    public String list() {

        String wiseSayings =  wiseSayingService.findAll().stream()
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
    @Transactional  // 식별자로 wiseSaying을 찾고 삭제하는 과정까지는 하나로 묶이는게 맞다
    public String delete(
            @PathVariable int id    // @PathVariable은 path에 있는 값을 꺼내 쓰겠다는 의미다.
    ){
        //삭제할 대상 찾기
        WiseSaying wiseSaying = wiseSayingService.findById(id);
        wiseSayingService.delete(wiseSaying);

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
        WiseSaying wiseSaying = wiseSayingService.findById(id);
        wiseSayingService.modify(wiseSaying, content, author);

        return id+"번 명언이 수정되었습니다.";
    }

    //상세보기 기능
    @GetMapping("/wiseSayings/{id}")
    @ResponseBody
    public String detail(
            @PathVariable int id
    ){
        WiseSaying wiseSaying = wiseSayingService.findById(id);

        // 파서 & 렌더러 준비 -> bean으로 해결
        // HTML 변환

        String html = markdownService.toHtml(wiseSaying.getContent());

        return """
                <h1>번호 : %s</h1>
                <div>작가 : %s</div>
                <div>명언 : %s</div>
                """.formatted(wiseSaying.getId(),  wiseSaying.getAuthor(), html);

                
    }

}
