import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParameterCodec } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

const httpOptions = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class WishListService{
    baseUrl: string = "http://localhost:8000/ZhcqRetailSystem-war/Resources/Wishlist";

  constructor(private httpClient: HttpClient) { }

  addToWishList(userId: number, productId: number): Observable<any>
  {
    return this.httpClient.post<any>(this.baseUrl + "/addToWishlist?userId=" + userId + "&productId=" + productId, null, httpOptions).pipe
    (
        catchError(this.handleError)
    );
	}
	
	retrieveWishList(userId : number): Observable<any>
	{
		return this.httpClient.get<any>(this.baseUrl + '/retrieveWishList?userId=' + userId).pipe
		(
			catchError(this.handleError)
		)
  }

	removeFromWishList(cartId:number, productId:number): Observable<any>
	{
			return this.httpClient.delete<any>(this.baseUrl + '?wishListId=' + cartId + '&productId='+ productId).pipe
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