package org.epita.exposition.dto.selection;

public class EtiquetteDto {
    private String nomTag;

    private Long idUtilisateur;

    public EtiquetteDto() {
    }

    public EtiquetteDto(String nomTag, Long idUtilisateur) {
        this.nomTag = nomTag;
        this.idUtilisateur = idUtilisateur;
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
