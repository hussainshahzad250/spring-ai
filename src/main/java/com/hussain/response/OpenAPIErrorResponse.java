package com.hussain.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OpenAPIErrorResponse {
    private Error error;
}
