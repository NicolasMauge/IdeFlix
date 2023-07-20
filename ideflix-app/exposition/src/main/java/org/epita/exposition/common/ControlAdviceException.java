package org.epita.exposition.common;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.common.IamException;
import org.epita.domaine.common.IamJsonConversionException;
import org.epita.domaine.common.IamUtilisateurExisteDejaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControlAdviceException extends ResponseEntityExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(ControlAdviceException.class);

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorModel> handleResourceNotFoundException(final EntityNotFoundException ex) {
        ErrorModel model = new ErrorModel("404", ex.getLocalizedMessage(), "Resource not found");
        return new ResponseEntity<ErrorModel>(model, HttpStatus.NOT_FOUND);
    }


    // ========================================================================================
    // Exceptions en réponse de l'IAM :
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorModel> handleIamException(final IamException ex) {
        ErrorModel model = new ErrorModel(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE), ex.getLocalizedMessage(), "Erreur de communication avec l'IAM");
        return new ResponseEntity<ErrorModel>(model, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorModel> handleIamUtilisateurExisteDejaException(final IamUtilisateurExisteDejaException ex) {
        ErrorModel model = new ErrorModel(String.valueOf(HttpStatus.CONFLICT), ex.getLocalizedMessage(), "L'utilisateur existe déjà.");
        return new ResponseEntity<ErrorModel>(model, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorModel> handleIamJsonConversionException(final IamJsonConversionException ex) {
        ErrorModel model = new ErrorModel(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getLocalizedMessage(), "Problème de conversion JSON en provenance de l'IAM.");
        return new ResponseEntity<ErrorModel>(model, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // ========================================================================================
    // Exceptions de validation :

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(" ; "));


        ErrorModel model = new ErrorModel(String.valueOf(HttpStatus.BAD_REQUEST), "Erreur de format.", validationErrors);

        logger.error("IdeFlix - Erreur de format : " + validationErrors);
        logger.error("IdeFlix - Erreur de format : " + ex.getLocalizedMessage());

        return new ResponseEntity<Object>(model, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        String validationErrors = ex.getConstraintViolations()
                .stream().
                map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(" ; "));

        ErrorModel model = new ErrorModel(String.valueOf(HttpStatus.BAD_REQUEST), ex.getLocalizedMessage(), "Erreur de contrainte : " + validationErrors);

        logger.error("IdeFlix - Erreur de contrainte : " + validationErrors);

        return new ResponseEntity<Object>(model, HttpStatus.BAD_REQUEST);
    }


}
