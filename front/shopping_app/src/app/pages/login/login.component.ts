import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    public loginForm = this.formBuilder.group({
        username : ['', Validators.required],
        password : ['', Validators.required]
    });

    constructor(
      private authService: AuthService,
      private formBuilder: FormBuilder,
      private router: Router
    ){}

    ngOnInit(): void {
    }

    authenticate(){
      const username = this.loginForm.get("username")?.value;
      const password = this.loginForm.get("password")?.value;

      this.authService.auth(username!, password!).subscribe({
        next: (resp) => {
          const { token } = resp;
          sessionStorage.setItem('token', token);
          console.log(token);
          this.router.navigate(['/products']);
        }
      });
    }
}
