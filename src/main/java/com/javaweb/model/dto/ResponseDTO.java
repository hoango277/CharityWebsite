package com.javaweb.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDTO {
    private Object data;
    private String message;
    private String detail;
}
