package com.example.clinicmanagement.model;
import com.example.clinicmanagement.Excpetion.ErrorResponse;
import com.example.clinicmanagement.Excpetion.DoctorException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class DoctorExceptionHandler extends ResponseEntityExceptionHandler{
  @ExceptionHandler(DoctorException.class)
  public final ResponseEntity<ErrorResponse> handleUserException(DoctorException ex, WebRequest request) {
      ErrorResponse error = new ErrorResponse(ex.getErrorMessage());
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
