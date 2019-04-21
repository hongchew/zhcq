import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { Member } from '../entities/member';
import { CoordinatedOutfitsPageModule } from '../coordinated-outfits/coordinated-outfits.module';




// const httpOptions = {
// 	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
// };



@Injectable({
  providedIn: 'root'
})

export class MemberService 
{
// tslint:disable: indent
	baseUrl: string = "http://localhost:8080/ZhcqRetailSystem-war/Resources/Member";

	// headers = new HttpHeaders();
	

	constructor(private httpClient: HttpClient)
	{
	}
	

	retrieveAllMembers(): Observable<any>
	{
		return this.httpClient.get<any>(this.baseUrl + "/retrieveAllMembers").pipe
		(
			catchError(this.handleError)
		)
	}

	retrieveMember(id: number): Observable<any> 
	{
		return this.httpClient.get<any>(this.baseUrl + "/retrieveMember/" + id).pipe
		(
			catchError(this.handleError)
		);
	}

	login(username: string, password: string): Observable<any>
	{
		// var headers = new HttpHeaders();
		// headers = headers.append('Content-Type', 'application/json');
		// headers = headers.append('Access-Control-Allow-Origin', '*');

		return this.httpClient.post<any>(this.baseUrl + '/login?username=' + username + "&password=" + password, null).pipe
		(
			catchError(this.handleError)
		);

	}

	createMember(firstName: string, lastName: string, username : string, password : string, email : string): Observable<any> {
		let createMemberReq = {'firstName' : firstName, 'lastName' : lastName, 'username': username, 'password': password, 'email' : email };

		return this.httpClient.put<any>(this.baseUrl, createMemberReq).pipe
		(
			catchError(this.handleError)
		);
	}

	updateMember(member: Member): Observable<any>
	{
		let updateMemberReq = {"member": member};
		var headers = new HttpHeaders();
		headers.append('Content-Type', 'application/json');
		headers.append('Access-Control-Allow-Origin', '*');
		return this.httpClient.post<any>(this.baseUrl, updateMemberReq, { headers }).pipe
		(
			catchError(this.handleError)
		);
	}
	
	
	
	deleteMember(id: number): Observable<any> 
	{
		var headers = new HttpHeaders();
		headers.append('Access-Control-Allow-Origin', '*');
		return this.httpClient.delete<any>(this.baseUrl + "/" + id,{ headers }).pipe
		(			
			catchError(this.handleError)
		);
	}
	
	
	
	private handleError(error: HttpErrorResponse)
	{
		if (error.error !== null) {
			let errorMessage: string = '';
		
			if (error.error instanceof ErrorEvent) 
			{
				errorMessage = 'An unknown error has occurred: ' + error.error.message;
			} 
			else 
			{		
				errorMessage = 'A HTTP error has occurred: ' + `HTTP ${error.status}: ${error.error.message}`;
			}
			
			console.error(errorMessage);
			
			return throwError(errorMessage);

		}
	}
}
