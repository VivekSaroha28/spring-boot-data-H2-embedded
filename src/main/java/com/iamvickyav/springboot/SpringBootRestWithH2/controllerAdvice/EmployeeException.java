package com.iamvickyav.springboot.SpringBootRestWithH2.controllerAdvice;

public class EmployeeException extends RuntimeException
{
    private String exceptionMsg;

    public EmployeeException(String exceptionMsg, Throwable ex){
        super(exceptionMsg, ex);
    }
}
