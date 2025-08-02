package com.hussain.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hussain.constant.Constant;
import com.hussain.response.OpenAPIErrorResponse;
import com.hussain.response.Response;
import lombok.extern.slf4j.Slf4j;

import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler implements Constant {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleException(Exception ex) {
        log.info("Exception  occurs => {}", ex.getMessage());
        return new ResponseEntity<>(new Response(ERROR_MSG, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NonTransientAiException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNonTransientAiException(NonTransientAiException ex) throws JsonProcessingException {
        Map<String, Object> errorResponse = new HashMap<>();
        try {
            errorResponse.put("status", HttpStatus.TOO_MANY_REQUESTS);
            errorResponse.put("code", HttpStatus.TOO_MANY_REQUESTS.value());
            errorResponse.put(MESSAGE, OPEN_API_QUOTA_EXCEEDED);
            log.info("Error Response from OpenAPI {}", ex.getMessage());
            String substring = ex.getMessage().substring(ex.getMessage().indexOf("- ") + 1);
            OpenAPIErrorResponse openAPIErrorResponse = new ObjectMapper().readValue(substring, OpenAPIErrorResponse.class);
            if (openAPIErrorResponse != null && openAPIErrorResponse.getError() != null && StringUtils.hasText(openAPIErrorResponse.getError().getMessage())) {
                errorResponse.put(MESSAGE, openAPIErrorResponse.getError().getMessage());
            }
        } catch (Exception exception) {
            log.error("some error occurred while preparing response");
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorResponse);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<Object> handleFileNotFoundException(HttpRequestMethodNotSupportedException ex) {
        log.info("HttpRequestMethodNotSupportedException occurs => {}", ex.getMessage());
        return new ResponseEntity<>(new Response(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<Object> handleFileNotFoundException(MissingServletRequestParameterException ex) {
        log.info("MissingServletRequestParameterException Exception occurs => {}", ex.getMessage());
        return new ResponseEntity<>(new Response(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}