package org.epita.exposition.dto.selection;

public class EtiquetteDto {
    private Long id;
    private String nomTag;

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
