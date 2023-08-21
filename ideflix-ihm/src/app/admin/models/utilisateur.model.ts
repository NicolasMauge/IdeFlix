export class UtilisateurModel {
    nom: string;
    prenom: string;
    email: string;
    roles: string[];


    // constructor(nom: string, prenom: string, email: string, roles: string[]) {
    //   this.nom = nom;
    //   this.prenom = prenom;
    //   this.email = email;
    //   this.roles = roles;
    // }

    constructor(utilisateurFromApi: any) {
        this.nom = utilisateurFromApi.nom;
        this.prenom = utilisateurFromApi.prenom;
        this.email = utilisateurFromApi.email;

        this.roles = utilisateurFromApi.roles != undefined ?
            utilisateurFromApi.roles.map((item: string) => item) :
            [...utilisateurFromApi.roles];
    }
}
