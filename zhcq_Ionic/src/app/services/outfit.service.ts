import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root'
})

export class OutfitService {
<<<<<<< HEAD
    baseUrl: string = 'http://localhost:8080/ZhcqRetailSystem-war/Resources/Coordinatedoutfit'
=======
    baseUrl: string = 'http://localhost:8000/ZhcqRetailSystem-war/Resources/CoordinatedOutfit';
>>>>>>> 8a130d5e04129a3b5bbeb48e7c0b0fa0e308fc27

    constructor(private httpClient: HttpClient) {

    }

    retrieveAllOutfits(): Observable<any> {
        return this.httpClient.get<any>(this.baseUrl + '/retrieveAllOutfits').pipe
        (
        catchError(this.handleError)
        );
    }

    retrieveOutfit(outfitId:number): Observable<any>  {
        return this.httpClient.get<any>(this.baseUrl + '/retrieveOutfitById/' + outfitId).pipe
        (
            catchError(this.handleError)
        );
    }

    private handleError(error: HttpErrorResponse) {
        let errorMessage: string = '';

        if (error.error instanceof ErrorEvent)
        {
            errorMessage = 'An unknown error has occurred: ' + error.error.message;
        } else {
            errorMessage = 'A HTTP error has occurred: ' + `HTTP ${error.status}: ${error.error.message}`;
        }
        console.error(errorMessage);

        return throwError(errorMessage);
    }
}