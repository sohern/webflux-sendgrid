package io.ztech.cloud.webfluxdemo.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmailResponse {
    private int statusCode;
    private String body;
    private String headers;
}
