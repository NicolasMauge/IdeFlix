import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, map, Observable} from "rxjs";
import {UtilisateurModel} from "../models/utilisateur.model";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UtilisateursService {

  USER_API: string = environment.USER_SERVER;

  _utilisateurs$: BehaviorSubject<any> = new BehaviorSubject(<UtilisateurModel[]>([]));

  constructor(private http: HttpClient) {
  }

  public getTousUtilisateurs(): void {
    let query_string: string = this.USER_API + "/admin/utilisateur/all";

    this.http.get(query_string)
      .subscribe((donnees: any) => this._utilisateurs$.next(donnees));
  }


  getValueOfUtilisateurs$(): UtilisateurModel[] {
    return this._utilisateurs$.getValue();
  }

  get utilisateurs$(): Observable<UtilisateurModel[]> {
    return this._utilisateurs$.asObservable();
  }

  supprimerUtilisateur(email: string) {
    console.log("supprimerUtilisateur (" + email + ")");
    let query_string: string = this.USER_API + "/admin/utilisateur/" + email;
    return this.http.delete<any>(query_string);
  }
}
