package org.epita.domaine.selection;

import org.epita.domaine.utilisateur.UtilisateurEntity;

import javax.persistence.*;

@Entity
public class EtiquetteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomTag;

    @OneToOne
    private UtilisateurEntity utilisateurEntity;

    public EtiquetteEntity() {
    }

    public EtiquetteEntity(String nomTag, UtilisateurEntity utilisateurEntity) {
        this.nomTag = nomTag;
        this.utilisateurEntity = utilisateurEntity;
    }

    public EtiquetteEntity(Long id, String nomTag, UtilisateurEntity utilisateurEntity) {
        this.id = id;
        this.nomTag = nomTag;
        this.utilisateurEntity = utilisateurEntity;
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

    public UtilisateurEntity getUtilisateurEntity() {
        return utilisateurEntity;
    }

    public void setUtilisateurEntity(UtilisateurEntity utilisateurEntity) {
        this.utilisateurEntity = utilisateurEntity;
    }
}
