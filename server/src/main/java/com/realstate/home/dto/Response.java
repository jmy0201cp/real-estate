package com.realstate.home.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

    private String status;
    private T data;

    // 리턴하는 값 없을 때
    public static Response<Void> success() {
        return new Response<Void>("success", null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>("success", data);
    }

}
