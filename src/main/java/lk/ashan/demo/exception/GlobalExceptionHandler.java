package lk.ashan.demo.exception;

import lk.ashan.demo.exception.customexception.ResourceExistsException;
import lk.ashan.demo.exception.customexception.ResourceNotFoundException;
import lk.ashan.demo.exception.entity.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceExistsException.class)
    public ResponseEntity<Response> handleExistsException (ResourceExistsException e){
        return  new ResponseEntity<>(new Response( HttpStatus.CONFLICT.value(), e.getMessage()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Response> handleNotFountException (ResourceNotFoundException e){
        return  new ResponseEntity<>(new Response( HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage()),HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
