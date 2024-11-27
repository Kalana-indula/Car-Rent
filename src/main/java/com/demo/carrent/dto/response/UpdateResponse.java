package com.demo.carrent.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateResponse<T> {

    private String responseMessage;
    private T updatedData;
}
