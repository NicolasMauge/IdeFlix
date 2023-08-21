import {Component, OnDestroy, OnInit} from '@angular/core';
import {UtilisateurModel} from "../../models/utilisateur.model";
import {Subscription} from "rxjs";
import {UtilisateursService} from "../../services/utilisateurs.service";

@Component({
  selector: 'app-admin-utilisateurs',
  templateUrl: './admin-utilisateurs.component.html',
  styleUrls: ['./admin-utilisateurs.component.css']
})
export class AdminUtilisateursComponent implements OnInit, OnDestroy {
  // utilisateurs: UtilisateurModel[] = [
  //   {nom: "Utilisateur1", prenom: "Util", email: "user@test.com", roles: ["ROLE_UTILISATEUR"]},
  //   {nom: "ADMIN", prenom: "Admin", email: "admin@test.com", roles: ["ROLE_UTILISATEUR", "ROLE_ADMIN"]}
  //   ];

  utilisateurs: UtilisateurModel[] = [];
  souscription!: Subscription;

  constructor(private service: UtilisateursService) {
  }

  ngOnInit(): void {
    this.souscription = this.service
      .getTousUtilisateurs()
      .subscribe(data => this.utilisateurs = data)
    ;
  }


  ngOnDestroy(): void {
    this.souscription.unsubscribe();
  }


  displayRole(roleSource: string): string {

    let roleAffichage: string = "";

    switch (roleSource) {
      case "ROLE_ADMIN":
        roleAffichage = "Administrateur";
        break;
      case "ROLE_UTILISATEUR":
        roleAffichage = "Utilisateur";
        break;
      default:
        roleAffichage = roleSource;
    }

    return roleAffichage;
  }

}
