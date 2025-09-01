package com.back.domain.wiseSaying.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor  //JPA는 기본생성자로 값을 세팅하기 때문에 AllArgsConstructor가 아니라 NoArgsConstructor가 맞다.
public class WiseSaying {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private String author;

    public WiseSaying(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public void update(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public boolean isNew() {
        return this.id == 0;
    }
}
