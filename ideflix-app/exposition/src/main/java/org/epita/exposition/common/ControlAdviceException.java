package org.epita.exposition.common;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.common.IamException;
import org.epita.domaine.common.IamJsonConversionException;
import org.epita.domaine.common.IamUtilisateurExisteDejaException;
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


}
