import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../../services/security/auth.service';
import {TokenStorageService} from '../../../services/security/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public registerForm!: FormGroup;

  constructor(private authService: AuthService,
              private tokenService: TokenStorageService,
              private router: Router,
              private fb: FormBuilder) { }

  ngOnInit(): void {
    this.registerForm = this.createRegisterForm();
  }

  createRegisterForm(): FormGroup {
    return this.fb.group({
      username: ['', Validators.compose([Validators.required])],
      login: ['', Validators.compose([Validators.required])],
      password: ['', Validators.compose([Validators.required])],
      confirmPassword: ['', Validators.compose([Validators.required])]
    });
  }

  submit(): void {
    console.log(this.registerForm.value);

    this.authService.register({
      username: this.registerForm.value.username,
      login: this.registerForm.value.login,
      password: this.registerForm.value.password,
      confirmPassword: this.registerForm.value.confirmPassword
    }).subscribe(data => {
      console.log(data);
      alert('Successfully registered');

    }, error => {
      console.log(error);
      alert(error.message);
    });
  }
}
