package org.epita.ideflixiam.exceptions;

import org.epita.ideflixiam.application.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GestionnaireExceptions { // aka GlobalExceptionHandler

    private static final Logger logger = LoggerFactory.getLogger(GestionnaireExceptions.class);

    @ExceptionHandler(ErreurFormatLoginException.class)
    public ResponseEntity<MessageExceptionDto> handleErreurFormatLoginException(ErreurFormatLoginException exception) {
        MessageExceptionDto messageExceptionDto = new MessageExceptionDto("Format d'entrée erroné.",
                exception.getMessage(),
                LocalDateTime.now());

        logger.debug("IAM - ErreurFormatLoginException");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400
                .body(messageExceptionDto);
    }


    @ExceptionHandler(RoleInexistantException.class)
    public ResponseEntity<MessageExceptionDto> handleRoleInexistantException(RoleInexistantException exception) {
        MessageExceptionDto messageExceptionDto = new MessageExceptionDto("Rôle non trouvé",
                exception.getMessage(),
                LocalDateTime.now());

        logger.debug("IAM - RoleInexistantException");

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // 404
                .body(messageExceptionDto);
    }

    @ExceptionHandler(UtilisateurExistantDejaException.class)
    public ResponseEntity<MessageExceptionDto> handleUtilisateurExistantDejaException(UtilisateurExistantDejaException exception) {
        MessageExceptionDto messageExceptionDto = new MessageExceptionDto("La syntaxe de la requête mais l'email est déjà utilisé.",
                exception.getMessage(),
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409
                .body(messageExceptionDto);
    }

    @ExceptionHandler(UtilisateurInexistantException.class)
    public ResponseEntity<MessageExceptionDto> handleUtilisateurInexistantException(UtilisateurInexistantException exception) {

        MessageExceptionDto messageExceptionDto = new MessageExceptionDto("Utilisateur ou mot de passe erroné.",
                exception.getMessage(),
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)  // 403
                .body(messageExceptionDto);
    }


    @ExceptionHandler(IdeFlixIamException.class)
    public ResponseEntity<MessageExceptionDto> handleIdeFlixIamException(IdeFlixIamException exception) {
        MessageExceptionDto messageExceptionDto = new MessageExceptionDto("Erreur IAM générique.",
                exception.getMessage(),
                LocalDateTime.now());

        logger.debug("IAM - Erreur imprévue");

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // TODO à voir si on peut mettre un meilleur code que 500
                .body(messageExceptionDto);
    }

}
