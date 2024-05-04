package com.amudy.response;


import lombok.Getter;

@Getter
public class ResData<T> {
    private T data;

    public ResData(T data){
        this.data = data;
    }
}
