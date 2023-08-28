package org.epita.exposition.dto.selection;

import io.swagger.v3.oas.annotations.media.Schema;

public class EtiquetteDto {

    @Schema(description = "Identifiant interne d'une étiquette dans IdeFlix.")
    private Long id;
    @Schema(description = "Libellé de l'étiquette choisi par l'utilisateur", example = "Santé")
    private String nomTag;
    @Schema(description = "Identifiant interne d'un utilisateur dans IdeFlix.")
    private Long idUtilisateur;

    public EtiquetteDto() {
    }

    public EtiquetteDto(Long id, String nomTag, Long idUtilisateur) {
        this.id = id;
        this.nomTag = nomTag;
        this.idUtilisateur = idUtilisateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTag() {
        return nomTag;
    }

    public void setNomTag(String nomTag) {
        this.nomTag = nomTag;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
