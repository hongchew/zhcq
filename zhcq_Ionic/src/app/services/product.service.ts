import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';


const httpOptions = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root'
})

export class ProductService
{
    baseUrl: string = "http://localhost:8000/Is3106Lecture10-war/Resources/Book"


    constructor(private httpClient: HttpClient)
    {

    }

    retrieveAllProducts(): Observable<any>
    {
        return this.httpClient.get<any>(this.baseUrl + "/retrieveAllProducts").pipe
        (
            catchError(this.handleError)
        );
    } 



    private handleError(error: HttpErrorResponse)
    {
        let errorMessage: string = "";
        
        if (error.error instanceof ErrorEvent) 
        {		
            errorMessage = "An unknown error has occurred: " + error.error.message;
        } 
        else 
        {		
            errorMessage = "A HTTP error has occurred: " + `HTTP ${error.status}: ${error.error.message}`;
        }
        
        console.error(errorMessage);
        
        return throwError(errorMessage);		
    }
}


