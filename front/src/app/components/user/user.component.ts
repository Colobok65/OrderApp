import { Component, OnInit } from '@angular/core';
import {User} from '../../models/User';
import {UserService} from '../../services/user/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: User = {login: '', username: '', orders: []};

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getCurrentUser();
  }

  getCurrentUser(): void {
    this.userService.getCurrentUser().subscribe(data => this.user = data);
  }

}
