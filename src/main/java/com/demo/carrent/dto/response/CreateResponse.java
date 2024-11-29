package com.demo.carrent.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateResponse<T> {

    private String statusMessage;
    private T createdData;
}
