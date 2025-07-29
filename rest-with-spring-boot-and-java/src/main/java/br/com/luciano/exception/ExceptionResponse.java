package br.com.luciano.exception;

import java.time.LocalDateTime;
import java.util.Date;

public record ExceptionResponse(String date, String message, String details ) {
}
