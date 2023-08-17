export class EtiquetteModel {
  nomTag: string;
  idUtilisateur: number;

  constructor(etiquetteFromApi: any) {
    this.nomTag = etiquetteFromApi.nomTag;
    this.idUtilisateur = etiquetteFromApi.idUtilisateur;
  }
}
