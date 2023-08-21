export class EtiquetteModel {
  id: number;
  nomTag: string;
  idUtilisateur: number;

  constructor(etiquetteFromApi: any) {
    this.id = etiquetteFromApi.id;
    this.nomTag = etiquetteFromApi.nomTag;
    this.idUtilisateur = etiquetteFromApi.idUtilisateur;
  }
}
