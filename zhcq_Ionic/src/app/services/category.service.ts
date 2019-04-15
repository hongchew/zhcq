import { HttpClient, HttpHeaders, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';



const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
    providedIn: 'root'
})

export class CategoryService 
{

    baseUrl: string = "http://localhost:8080/ZhcqRetailSystem-war/Resources/Category";


    constructor(private httpClient: HttpClient)
    {
    }
    
    retrieveAllCategories(): Observable<any>
    {
        return this.httpClient.get<any>(this.baseUrl + "/retrieveAllCategories").pipe
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