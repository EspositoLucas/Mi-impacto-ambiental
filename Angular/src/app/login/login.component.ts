import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
    loginForm = this.formBuilder.nonNullable.group({
        username: ['', [Validators.required]],
        password: ['', [Validators.required]],
    });

    constructor(
        private formBuilder: FormBuilder,
        private authService: AuthService,
        private router: Router
    ) {}

    ngOnInit(): void {}

    onSubmit() {
        this.authService
            .login(this.username?.value, this.password?.value)
            .subscribe((response) => {
                if (response.success) {
                    this.router.navigate(['/']);
                } else {
                    this.loginForm.setErrors({
                        loginError: response.errorMessage,
                    });
                }
            });
    }

    get username() {
        return this.loginForm.get('username')!;
    }

    get password() {
        return this.loginForm.get('password')!;
    }
}
