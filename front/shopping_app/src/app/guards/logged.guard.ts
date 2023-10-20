import { CanActivate, Router } from "@angular/router";
import { AuthService } from "../services/auth/auth.service";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class LoggedGuard implements CanActivate {
  constructor(
    private authService:AuthService,
    private router:Router
  ){

  }
  canActivate(): boolean{
     if (!this.authService.isAuthenticated()){
       return true;
     }
     this.router.navigate(['/products']);
     return false;
  }
}