package com.iumutdikbasan.tripSearch.business.exceptionHandlers;


import com.iumutdikbasan.tripSearch.common.exceptions.BusinessException;
import com.iumutdikbasan.tripSearch.common.results.ErrorDataResult;
import com.iumutdikbasan.tripSearch.common.results.ErrorResult;
import com.iumutdikbasan.tripSearch.common.results.Result;
import com.iumutdikbasan.tripSearch.common.results.ResultMessage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    public Result handleBusinessException(BusinessException businessException){
        return new ErrorResult(businessException.getMessage());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    public Result handleInputException(HttpMessageNotReadableException httpMessageNotReadableException){
        return new ErrorResult(ResultMessage.BAD_JSON_BODY.toString());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Result handleValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        //Mapping validation errors.
        Map<String,String> validationErrors = new HashMap<>();
        for(FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorDataResult<Map<String,String>>(validationErrors,ResultMessage.BAD_INPUT.toString());
    }
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Result handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        return new ErrorResult("Böyle bir varlık bulunamadı");
    }
}