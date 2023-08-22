import {Component, OnDestroy, OnInit} from '@angular/core';
import {UtilisateurModel} from "../../models/utilisateur.model";
import {Subscription} from "rxjs";
import {UtilisateursService} from "../../services/utilisateurs.service";
import {MessageService} from "../../../core/services/common/message.service";

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

  constructor(private service: UtilisateursService,
              private messageService: MessageService) {
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

  supprimerUtilisateur(email: string) {


    this.service.supprimerUtilisateur(email).subscribe(
      res => {
        this.messageService.show("Utilisateur " + email + " supprimé.", "success");

      },
      err => {
        this.messageService.show("Suppression de " + email + " impossible.", "error");
      }
    );
  }
}
