import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, map, Observable} from "rxjs";
import {UtilisateurModel} from "../models/utilisateur.model";

@Injectable({
    providedIn: 'root'
})
export class UtilisateursService {

    private _utilisateurs$ = new BehaviorSubject(<UtilisateurModel[]>([]));

    constructor(private http: HttpClient) {
    }

    getTousUtilisateurs() {
        let query_string: string = "http://localhost:8081/api/v1/iam/admin/utilisateur/all";

        return this.http.get<UtilisateurModel[]>(query_string);
    }

    getValueOfUtilisateurs$(): UtilisateurModel[] {
        return this._utilisateurs$.getValue();
    }

    get utilisateurs$(): Observable<UtilisateurModel[]> {
        return this._utilisateurs$.asObservable();
    }
}