package com.back;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonRepository {

    private final int version;  //final이고 @RequiredArgsConstructor라서 생성자의 입력값이 세팅됨

    public int count() {

        System.out.println("version = " + version);
        return 3;
    }
}
