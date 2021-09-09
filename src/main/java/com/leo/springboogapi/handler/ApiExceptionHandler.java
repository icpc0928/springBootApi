package com.leo.springboogapi.handler;

import com.leo.springboogapi.exception.InvalidRequestException;
import com.leo.springboogapi.exception.NotFoundException;
import com.leo.springboogapi.resource.ErrorResource;
import com.leo.springboogapi.resource.FieldResource;
import com.leo.springboogapi.resource.InvalidErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

//攔截所有Rest
@RestControllerAdvice
public class ApiExceptionHandler {
    /**
     * 處理資源找不到異常
     * @param e
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody   //JSON格式
    public ResponseEntity<?> handlerNotFound(RuntimeException e){
        ErrorResource errorResource = new ErrorResource(e.getMessage());
        return new ResponseEntity<Object>(errorResource, HttpStatus.NOT_FOUND);
    }

    /**
     * 處理參數驗證失敗
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    public ResponseEntity<?> handlerInvalidRequest(InvalidRequestException e){
        Errors errors = e.getErrors();
        List<FieldResource> fieldResources = new ArrayList<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for(FieldError fieldError : fieldErrors){
            FieldResource fieldResource = new FieldResource(
                    fieldError.getObjectName(),
                    fieldError.getField(),
                    fieldError.getCode(),
                    fieldError.getDefaultMessage());
            fieldResources.add(fieldResource);
        }
        InvalidErrorResource ier = new InvalidErrorResource(e.getMessage(), fieldResources);
        return new ResponseEntity<Object>(ier, HttpStatus.BAD_REQUEST);
    }

    /**
     * 處理全局異常(500)
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handlerException(Exception e){
        return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
