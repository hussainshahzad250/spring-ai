package com.hussain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {

    private String message;
    private String type;
    private Object param;
    private String code;

}