import {Component, OnInit} from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import {MenuItem} from 'primeng/api';
import {TokenStorageService} from './services/security/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'OrderApp';

  items: MenuItem[] = [];
  isUserLoggedIn = false;

  constructor(private primengConfig: PrimeNGConfig,
              private tokenService: TokenStorageService) {}

  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.items = [
      {label: 'Заказы', icon: 'pi pi-folder-open', routerLink: ['order']},
      {label: 'Товары', icon: 'pi pi-shopping-cart', routerLink: ['goods']},
      {label: 'Мои данные', icon: 'pi pi-user', routerLink: ['user']}
    ];

    const currentUser = this.tokenService.getUser();
    if (currentUser) {
      this.isUserLoggedIn = true;
    }
  }

  logout(): void {
    const result = confirm('Вы действительно хотите выйти?');
    if (result) {
      sessionStorage.clear();
      location.reload();
    }
  }
}
