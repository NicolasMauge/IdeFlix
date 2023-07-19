package org.epita.infrastructure.utilisateur.iam.apidto;

import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UtilisateurIamApiMapper {

    public UtilisateurIamCreationApiDto mapEntityToCreationApiDto(UtilisateurIamEntity utilisateurIamEntity) {
        return new UtilisateurIamCreationApiDto(
                utilisateurIamEntity.getNom(),
                utilisateurIamEntity.getPrenom(),
                utilisateurIamEntity.getEmail(),
                utilisateurIamEntity.getMotDePasse()
        );
    }

    public UtilisateurIamEntity mapCreationReponseDtoToEntity(UtilisateurIamCreationReponseApiDto utilisateurIamCreationReponseApiDto) {

        LocalDateTime dateCreationIam;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        dateCreationIam = LocalDateTime.parse(utilisateurIamCreationReponseApiDto.getDateCreation(),
                dateTimeFormatter);


        return new UtilisateurIamEntity(
                utilisateurIamCreationReponseApiDto.getNom(),
                utilisateurIamCreationReponseApiDto.getPrenom(),
                utilisateurIamCreationReponseApiDto.getEmail(),
                dateCreationIam
        );
    }

}
