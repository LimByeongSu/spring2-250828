package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WiseSayingService {

    private final WiseSayingRepository wiseSayingMemRepository;

    //ApplicationRunner는 모든 준비가 끝마치고 실행되는거라
    //ApplicationRunner에서 하는게 제일 안전함


    /*@PostConstruct//객체가 다 생성이 되고난 후에 실행되는 것인데 이것도 비추
    public void init(){
        write("명언1", "작가1");
        write("명언2", "작가2");
        write("명언3", "작가3");
        write("명언4", "작가4");
        write("명언5", "작가5");
    }*/

    //생성자에서 샘플 데이터를 생성하는 방식은 바람직 하지않다.
    /*public WiseSayingService() {
        write("명언1", "작가1");
        write("명언2", "작가2");
        write("명언3", "작가3");
        write("명언4", "작가4");
        write("명언5", "작가5");
        
    }*/

    public WiseSaying write(String content, String author) {

        WiseSaying wiseSaying = new WiseSaying(content, author);
        wiseSayingMemRepository.save(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return wiseSayingMemRepository.findAll();
    }

    public WiseSaying findById(int id) {
        Optional<WiseSaying> wiseSaying = wiseSayingMemRepository.findById(id);

        if(wiseSaying.isEmpty()) {
            throw new RuntimeException("%d번 명언은 존재하지 않습니다.".formatted(id));
        }
        return wiseSaying.get();
    }

    public void delete(WiseSaying wiseSaying) {
        wiseSayingMemRepository.delete(wiseSaying);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.update(content, author);
    }

    public long count(){
        return wiseSayingMemRepository.count();
    }
}