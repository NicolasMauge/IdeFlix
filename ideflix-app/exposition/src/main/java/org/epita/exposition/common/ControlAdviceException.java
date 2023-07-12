package org.epita.exposition.common;

import org.epita.domaine.common.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControlAdviceException extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorModel> handleResourceNotFoundException(final ResourceNotFoundException ex) {
        ErrorModel model = new ErrorModel("404", ex.getLocalizedMessage(), "Resource not found");
        return new ResponseEntity<ErrorModel>(model, HttpStatus.NOT_FOUND);
    }
}
