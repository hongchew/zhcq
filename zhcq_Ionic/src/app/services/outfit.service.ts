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
    baseUrl: string = 'http://localhost:8080/ZhcqRetailSystem-war/Resources/Coordinatedoutfit'

    constructor(private httpClient: HttpClient) {

    }

    retrieveAllOutfits(): Observable<any> {
        return this.httpClient.get<any>(this.baseUrl + '/retrieveAllOutfits').pipe
        (
        catchError(this.handleError)
        );
    }

    retrieveOutfit(): Observable<any>  {
        return this.httpClient.get<any>(this.baseUrl + '/retrieveOutfitById').pipe
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