package tech.caju.authorizer.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {RuntimeException.class, InsuficientAmountException.class})
  protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {

    var body = "{\"code\": \"%s\"}";
    if (ex instanceof InsuficientAmountException iam) {
      body = body.replace("%s", iam.getCode());
    } else {
      body = body.replace("%s", "07");
    }

    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return handleExceptionInternal(ex, body, headers, HttpStatus.OK, request);
  }
}
