import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParameterCodec } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SalestransactionService {

  baseUrl: string = 'http://localhost:8080/ZhcqRetailSystem-war/Resources/SaleTransaction';

  constructor(private httpClient: HttpClient) { 

  }

  retrieveSalesTransactionByUserId(userId : number) : Observable<any>{
    return this.httpClient.get<any>(this.baseUrl + '?userId=' + userId).pipe
		(
			catchError(this.handleError)
		)
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
