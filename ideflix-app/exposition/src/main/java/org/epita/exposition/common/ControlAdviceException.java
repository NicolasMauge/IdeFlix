package org.epita.exposition.common;

import org.epita.domaine.common.*;
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
import java.util.stream.Collectors;

@ControllerAdvice
public class ControlAdviceException extends ResponseEntityExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(ControlAdviceException.class);

    // ========================================================================================
    // méthode privée commune pour créer le retour :
    private ResponseEntity<Object> getResponseEntity(String messageErreur, HttpStatus httpStatus, Exception exception) {

        ErrorModel model = new ErrorModel(String.valueOf(httpStatus),
                exception.getLocalizedMessage(),
                messageErreur);

        logger.debug("IdeFlix - Envoi vers l'IHM : " + messageErreur + " - Code " + httpStatus + ".");

        return new ResponseEntity<>(model, httpStatus);
    }


    // ========================================================================================
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Object> handleResourceNotFoundException(final EntityNotFoundException ex) {
        return getResponseEntity("Resource not found", HttpStatus.NOT_FOUND, ex);
    }


    // ========================================================================================
    // Exceptions en réponse de l'IAM :
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Object> handleIamUtilisateurExisteDejaException(final IamUtilisateurExisteDejaException ex) {
        return getResponseEntity("L'utilisateur existe déjà.", HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Object> handleIamUtilisateurInterditException(final IamUtilisateurInterditException ex) {
        return getResponseEntity("Connexion refusée : email ou mot de passe erroné.", HttpStatus.FORBIDDEN, ex);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Object> handleIamException(final IamException ex) {
        return getResponseEntity("Erreur de communication avec l'IAM", HttpStatus.SERVICE_UNAVAILABLE, ex);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Object> handleIamJsonException(final IamJsonException ex) {
        return getResponseEntity("Problème de conversion du JSON reçu de l'IAM", HttpStatus.SERVICE_UNAVAILABLE, ex);
    }


    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Object> handleIamErreurHabilitation(final IamErreurHabilitationException ex) {
        return getResponseEntity("Habilitation : l'utilisateur n'est pas autorisé à accéder à cette ressource.", HttpStatus.FORBIDDEN, ex);
    }

    //========================================================================================
    //Exceptions en réponse de MovieDataBase:
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Object> handleMovieDataBaseMediaNontrouveException(final MediaDataBaseNonTrouveException ex) {
        return getResponseEntity("Media recherché non trouvé", HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Object> handleMovieDataBaseUnAuthorizedAccessException(final MediaDataBaseUnAuthorizedAccessException ex) {
        return getResponseEntity("l'accès à la ressource est non autorisé", HttpStatus.FORBIDDEN, ex);
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

        logger.error("IdeFlix - Erreur de format : " + validationErrors);
        logger.error("IdeFlix - Erreur de format : " + ex.getLocalizedMessage());

        return getResponseEntity("Erreur de format : " + validationErrors, HttpStatus.BAD_REQUEST, ex);

    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex,
                                                            WebRequest request) {

        String validationErrors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(" ; "));

        logger.error("IdeFlix - Erreur de contrainte : " + validationErrors);

        return getResponseEntity("Erreur de contrainte : " + validationErrors, HttpStatus.BAD_REQUEST, ex);
    }


}
