import { Injectable } from '@angular/core';
import jwtDecode from 'jwt-decode';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const TOKEN_PARSE = 'token-obj';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  public saveToken(token: string): void {
    sessionStorage.removeItem(TOKEN_KEY);
    sessionStorage.setItem(TOKEN_KEY, token);
  }

  saveParsedToken(token: string): void {
    sessionStorage.setItem(TOKEN_PARSE, JSON.stringify(jwtDecode(token)));
  }

  getObjectFromToken(): any {
    return JSON.parse(sessionStorage.getItem(TOKEN_PARSE) as string);
  }

  public getToken(): string | null {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any): void {
    sessionStorage.removeItem(USER_KEY);
    sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    return JSON.parse(sessionStorage.getItem(USER_KEY) as string);
  }

  logOut(): void {
    sessionStorage.clear();
    location.reload();
  }
}
