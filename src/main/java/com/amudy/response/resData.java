package com.amudy.response;


import lombok.Getter;

@Getter
public class resData<T> {
    private T data;

    public resData(T data){
        this.data = data;
    }
}
