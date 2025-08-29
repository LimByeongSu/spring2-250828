package com.back.domain.wiseSaying.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WiseSaying {
    private int id;
    private String content;
    private String author;

    public WiseSaying(int lastId, String content, String author) {
        this.id=lastId;
        this.content = content;
        this.author = author;
    }
}
