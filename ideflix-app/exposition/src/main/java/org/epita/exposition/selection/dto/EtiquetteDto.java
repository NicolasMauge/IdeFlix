package org.epita.exposition.selection.dto;

import org.epita.exposition.utilisateur.utilisateur.UtilisateurDto;

public class EtiquetteDto {
    private Long id;

    private String nomTag;

    private UtilisateurDto utilisateurDto;

    public EtiquetteDto() {
    }

    public EtiquetteDto(Long id, String nomTag, UtilisateurDto utilisateurDto) {
        this.id = id;
        this.nomTag = nomTag;
        this.utilisateurDto = utilisateurDto;
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

    public UtilisateurDto getUtilisateurDto() {
        return utilisateurDto;
    }

    public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
        this.utilisateurDto = utilisateurDto;
    }
}
