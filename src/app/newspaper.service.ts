import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';

import { NewsPaper } from './NewsPaper';

@Injectable()
export class NewspaperService {
    //URLs for CRUD operations
    allNewspapersUrl = "http://localhost:8090/aakash/newspapers";
    newspaperUrl = "http://localhost:8090/aakash/newspaper";
    //Create constructor to get Http instance
    constructor(private http:Http) {
    }
    //Fetch all newspapers
    getAllNewspapers(): Observable<NewsPaper[]> {
        return this.http.get(this.allNewspapersUrl)
	       .map(this.extractData)
	       .catch(this.handleError);
  }
    //Create newspaper
  createNewspaper(newspaper: NewsPaper):Observable<number> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.post(this.newspaperUrl, newspaper, options)
               .map(success => success.status)
               .catch(this.handleError);
    }
    //Fetch newspaper by id
  getNewspaperById(newspaperId: string): Observable<NewsPaper> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
	let cpParams = new URLSearchParams();
	cpParams.set('id', newspaperId);
	let options = new RequestOptions({ headers: cpHeaders,params : cpParams});
	return this.http.get(this.newspaperUrl, options)
		.map(this.extractData)
		.catch(this.handleError);
    }
  //Update newspaper
  updateNewspaper(newspaper: NewsPaper):Observable<number> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.put(this.newspaperUrl, newspaper, options)
               .map(success => success.status)
               .catch(this.handleError);
    }
  //Delete newspaper
  deleteNewspaperById(newspaperId: string): Observable<number> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
	let cpParams = new URLSearchParams();
	cpParams.set('id', newspaperId);
	let options = new RequestOptions({ headers: cpHeaders,params : cpParams });
	return this.http.delete(this.newspaperUrl, options)
	       .map(success => success.status)
	       .catch(this.handleError);
    }
  private extractData(res: Response) {
	let body = res.json();
        return body;
    }
  private handleError (error: Response | any) {
	console.error(error.message || error);
	return Observable.throw(error.status);
    }
}
