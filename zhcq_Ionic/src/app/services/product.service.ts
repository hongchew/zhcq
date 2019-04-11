import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';


const httpOptions = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root'
})

