package org.epita.exposition.utilisateur.utilisateur;

import java.time.LocalDate;

public class UtilisateurDto {
    private Long id;

    private String email;

    private LocalDate dateCreation;

    public UtilisateurDto() {
    }

    public UtilisateurDto(Long id, String email, LocalDate dateCreation) {
        this.id = id;
        this.email = email;
        this.dateCreation = dateCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
}
