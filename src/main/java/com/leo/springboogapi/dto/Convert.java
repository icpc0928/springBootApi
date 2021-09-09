package com.leo.springboogapi.dto;

public interface Convert<S, T> {
    //S:源 T: 目標對象
    T convert(S s, T t);

    T convert(S s);
}
