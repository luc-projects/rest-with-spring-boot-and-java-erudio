package br.com.luciano.exception.handler;


import br.com.luciano.exception.DivisionByZeroException;
import br.com.luciano.exception.ExceptionResponse;
import br.com.luciano.exception.UnsupportedMathOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedEntityResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
        LocalDateTime dateAndHour = LocalDateTime.now();
        String date = convertDate(dateAndHour);
        ExceptionResponse response = new ExceptionResponse(date, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UnsupportedMathOperationException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(UnsupportedMathOperationException ex, WebRequest request){
        LocalDateTime dateAndHour = LocalDateTime.now();
        String date = convertDate(dateAndHour);
        ExceptionResponse response = new ExceptionResponse(date, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DivisionByZeroException.class)
    public final ResponseEntity<ExceptionResponse> handleDivisionByZeroExceptions(DivisionByZeroException ex, WebRequest request){
        LocalDateTime dateAndHour = LocalDateTime.now();
        String date = convertDate(dateAndHour);
        ExceptionResponse response = new ExceptionResponse(date, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String convertDate(LocalDateTime dateAndHour){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        return dateAndHour.format(formatter);
    }
}
