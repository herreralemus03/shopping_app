import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { BehaviorSubject, map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BaseHttpService {

  public isLoading : boolean = false;
  public observableLoading : BehaviorSubject<boolean>;
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) {
    this.observableLoading = new BehaviorSubject<boolean>(this.isLoading);
  }

  eventChange(){
    this.observableLoading.next(this.isLoading);
  }

  private buildHttpParams(
    params : { [key: string]: string | number | boolean }
  ): HttpParams{
    const httpParams = new HttpParams().appendAll(params);
    return httpParams;
  }

  private buildHeaders(authenticate : boolean) : HttpHeaders {
    let header = new HttpHeaders();
    let other_header = header.append("Content-Type","application/json");
    if(authenticate) other_header = header.append("Authorization",`Bearer ${sessionStorage.getItem('token')}`);
    console.log(other_header)
    return other_header;
  }

  public getNoToken (
    path : string,
    params : { [key: string]: string | number | boolean } = {},
    authenticate : boolean = true
  ) : Observable<any> {
    this.isLoading = true;
    this.eventChange();
    return this.http.get(`${this.baseUrl}${path}`, { 
      headers: {'Content-Type': "application/json"},
      params: this.buildHttpParams(params)
    }).pipe(map( resp => {
      this.isLoading = false;
      this.eventChange();
      return resp;
    }));
  }

  public get (
    path : string,
    params : { [key: string]: string | number | boolean } = {},
    authenticate : boolean = true
  ) : Observable<any> {
    this.isLoading = true;
    this.eventChange();
    return this.http.get(`${this.baseUrl}${path}`, { 
      headers: {'Content-Type': "application/json", "Authorization": `Bearer ${sessionStorage.getItem('token')}`},
      params: this.buildHttpParams(params)
    }).pipe(map( resp => {
      this.isLoading = false;
      this.eventChange();
      return resp;
    }));
  }

  public post (
    path : string,
    body: any,
    params : { [key: string]: string | number | boolean } = {},
    authenticate : boolean = true
  ) : Observable<any> {
    this.isLoading = true;
    this.eventChange();
    return this.http.post(`${this.baseUrl}${path}`, body, { 
      headers: {'Content-Type': "application/json", "Authorization": `Bearer ${sessionStorage.getItem('token')}`},
      params: this.buildHttpParams(params), 
    }).pipe(map( resp => {
      this.isLoading = false;
      this.eventChange();
      return resp;
    }));
  }

  public postNoToken (
    path : string,
    body: any,
    params : { [key: string]: string | number | boolean } = {},
    authenticate : boolean = true
  ) : Observable<any> {
    this.isLoading = true;
    this.eventChange();
    return this.http.post(`${this.baseUrl}${path}`, body, { 
      headers: {'Content-Type': "application/json"},
      params: this.buildHttpParams(params), 
    }).pipe(map( resp => {
      this.isLoading = false;
      this.eventChange();
      return resp;
    }));
  }

  public put (
    path : string,
    body: any,
    params : { [key: string]: string | number | boolean } = {},
    authenticate : boolean = true
  ) : Observable<any> {
    this.isLoading = true;
    this.eventChange();
    return this.http.put(`${this.baseUrl}${path}`, body, { 
      headers: {'Content-Type': "application/json", "Authorization": `Bearer ${sessionStorage.getItem('token')}`},
      params: this.buildHttpParams(params)
    }).pipe(map( resp => {
      this.isLoading = false;
      this.eventChange();
      return resp;
    }));
  }
  
  public delete (
    path : string,
    params : { [key: string]: string | number | boolean } = {},
    authenticate : boolean = true
  ) : Observable<any> {
    this.isLoading = true;
    this.eventChange();
    return this.http.delete(`${this.baseUrl}${path}`, { 
      headers: {'Content-Type': "application/json", "Authorization": `Bearer ${sessionStorage.getItem('token')}`},
      params: this.buildHttpParams(params)
    }).pipe(map( resp => {
      this.isLoading = false;
      this.eventChange();
      return resp;
    }));
  }
}
