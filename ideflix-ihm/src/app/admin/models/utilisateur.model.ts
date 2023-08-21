import {RoleModel} from "./role.model";

export class UtilisateurModel {
  nom: string;
  prenom: string;
  email: string;
  roles: RoleModel[];

  constructor(utilisateurFromApi: any) {
    this.nom = utilisateurFromApi.nom;
    this.prenom = utilisateurFromApi.prenom;
    this.email = utilisateurFromApi.email;

    this.roles = utilisateurFromApi.roles != undefined ?
      utilisateurFromApi.roles.map((item: RoleModel) => item.nomRole) :
      [...utilisateurFromApi.roles];

  }
}
