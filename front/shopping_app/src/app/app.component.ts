import { Component } from '@angular/core';
import { AuthService } from './services/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'shopping_app';

  constructor(private authService: AuthService){}

  isAuthenticated() : boolean {
    return this.authService.isAuthenticated();
  } 
}
