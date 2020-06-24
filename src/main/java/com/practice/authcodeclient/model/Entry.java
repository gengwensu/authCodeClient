package com.practice.authcodeclient.model;

import lombok.Getter;

@Getter
public class Entry {
    private String value;

    public Entry(String value){
        this.value = value;
    }
}
