import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../../services/security/auth.service';
import {TokenStorageService} from '../../../services/security/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm!: FormGroup;

  constructor(private authService: AuthService,
              private tokenService: TokenStorageService,
              private router: Router,
              private fb: FormBuilder) {
    /* Проверка чтобы авторизованный пользователь не мог вернуться на страницу логина */
    if (this.tokenService.getUser()) {
      this.router.navigate(['/order']);
    }
  }

  ngOnInit(): void {
    this.loginForm = this.createLoginForm();
  }

  createLoginForm(): FormGroup {
    return this.fb.group({
      login: ['', Validators.compose([Validators.required])],
      password: ['', Validators.compose([Validators.required])]
    });
  }

  submit(): void {
    this.authService.login({
      login: this.loginForm.value.login,
      password: this.loginForm.value.password
    }).subscribe(data => {
      console.log(data);

      this.tokenService.saveToken(data.token);
      this.tokenService.saveUser(data);
      this.tokenService.saveParsedToken(data.token);

      this.router.navigate(['/order']);
      window.location.reload();
    }, error => {
      console.log(error);
      alert(error.message);
    });
  }

}
