package com.optum.datapublisher.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseWrapper<T> {

    private T payload;
    private String message;

    public ResponseWrapper(T payload){
        this.payload=payload;

    }
    public ResponseWrapper(String message){
        this.message=message;

    }
}
