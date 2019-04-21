import { HttpHeaders, HttpErrorResponse, HttpClient } from "@angular/common/http";
import { Observable, throwError } from 'rxjs';

import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
    providedIn: 'root'
})

export class PromotionService
{
    baseUrl: string = 'http://localhost:8080/ZhcqRetailSystem-war/Resources/Promotion';

    constructor(private httpClient: HttpClient)
    {

    }

    retrieveAllPromotions(): Observable<any>
    {
        return this.httpClient.get<any>(this.baseUrl + '/retrieveAllPromotions').pipe
        (
            catchError(this.handleError)
        )
    }



    private handleError(error: HttpErrorResponse)
    {
        if (error.error !== null) {
            let errorMessage: string = '';

            if (error.error instanceof ErrorEvent) {
                errorMessage = 'An unknown error has occurred: ' + error.error.message;
            } else {
                errorMessage = 'A HTTP error has occurred: ' + `HTTP ${error.status}: ${error.error.message}`;
            }
            console.error(errorMessage);
            return throwError(errorMessage);
        }
    }
}
