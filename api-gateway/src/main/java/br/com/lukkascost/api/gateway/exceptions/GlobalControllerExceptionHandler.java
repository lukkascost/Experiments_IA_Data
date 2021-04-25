package br.com.lukkascost.api.gateway.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleJsonParseException(HttpServletResponse response, Exception ex) throws IOException {
        log.error(ex.getMessage(), ex);
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
