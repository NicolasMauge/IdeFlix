package org.epita.ideflixiam.exceptions;

import org.epita.ideflixiam.application.common.RoleInexistantException;
import org.epita.ideflixiam.application.common.UtilisateurExistantDejaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GestionnaireExceptions { // aka GlobalExceptionHandler

    @ExceptionHandler(RoleInexistantException.class)
    public ResponseEntity<MessageExceptionDto> handleRoleInexistantException(RoleInexistantException exception) {
        MessageExceptionDto messageExceptionDto = new MessageExceptionDto("Rôle non trouvé",
                exception.getMessage(),
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(messageExceptionDto);
    }

    @ExceptionHandler(UtilisateurExistantDejaException.class)
    public ResponseEntity<MessageExceptionDto> handleUtilisateurExistantDejaException(UtilisateurExistantDejaException exception) {
        MessageExceptionDto messageExceptionDto = new MessageExceptionDto("La syntaxe de la requête mais l'email est déjà utilisé.",
                exception.getMessage(),
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN) // le 403 peut aussi servir pour les doublons.
                .body(messageExceptionDto);
    }
}
