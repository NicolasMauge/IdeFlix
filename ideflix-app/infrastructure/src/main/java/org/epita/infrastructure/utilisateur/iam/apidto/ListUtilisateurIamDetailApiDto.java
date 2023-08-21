package org.epita.infrastructure.utilisateur.iam.apidto;

import java.util.List;

// Cette classe sert juste à encapsuler la liste des utilisateurs pour traiter la réponse de l'IAM
public class ListUtilisateurIamDetailApiDto {

    List<UtilisateurIamDetailApiDto> utilisateurIamDetailApiDtoList;

    public ListUtilisateurIamDetailApiDto(List<UtilisateurIamDetailApiDto> utilisateurIamDetailApiDtoList) {
        this.utilisateurIamDetailApiDtoList = utilisateurIamDetailApiDtoList;
    }

    public ListUtilisateurIamDetailApiDto() {
    }


    public List<UtilisateurIamDetailApiDto> getUtilisateurIamDetailApiDtoList() {
        return utilisateurIamDetailApiDtoList;
    }

    public void setUtilisateurIamDetailApiDtoList(List<UtilisateurIamDetailApiDto> utilisateurIamDetailApiDtoList) {
        this.utilisateurIamDetailApiDtoList = utilisateurIamDetailApiDtoList;
    }
}
