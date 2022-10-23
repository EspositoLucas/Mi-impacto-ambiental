import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
    username?: string;
    isLoggedIn: boolean;

    constructor(private authService: AuthService) {
        this.isLoggedIn = authService.isAuthenticated;
        this.username = authService.loggedUser?.username;
    }

    ngOnInit(): void {}
}
