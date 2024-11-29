package com.demo.carrent.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteResponse {

    private String statusMessage;
    private Boolean isDeleted;
}
