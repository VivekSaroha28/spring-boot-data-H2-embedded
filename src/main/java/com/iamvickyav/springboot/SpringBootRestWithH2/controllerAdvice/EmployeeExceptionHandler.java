package com.iamvickyav.springboot.SpringBootRestWithH2.controllerAdvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class EmployeeExceptionHandler
{
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<Object> handleError(HttpServletRequest req, EmployeeException ex)
    {
        log.error("Employee Exception !!");
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(ex.getMessage());
        if (ex.getCause() != null)
            apiError.setDebugMessage(ex.getCause().getLocalizedMessage());
        apiError.setErrorType(ex.getClass().getSimpleName());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
