import { Injectable } from '@angular/core';
import { BaseHttpService } from '../base-http.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpService : BaseHttpService) { }

  public auth (username: string, password: string) : Observable<any> {
    const credentials = {username, password};
    return this.httpService.postNoToken('/security/auth', credentials, {}, false);
  }

  public isAuthenticated() : boolean {
    return sessionStorage.getItem('token') != null;
  }
}
