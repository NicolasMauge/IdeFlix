package org.epita.ideflixiam.domaine;

import javax.persistence.*;

@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomRole;

//    @ManyToMany(mappedBy = "listeRoles")
//    private List<UtilisateurIam> listeUtilisateursIam;


    public RoleEntity() {
    }

    public RoleEntity(String nomRole) {
        this.nomRole = nomRole;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
}
