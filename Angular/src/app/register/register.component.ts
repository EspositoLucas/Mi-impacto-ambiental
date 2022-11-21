import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import {
    RegistrarUsuarioRequest,
    RegistrarUsuarioResponse,
} from '../auth/authapi.service';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
    registrarRequest: RegistrarUsuarioRequest = newRegistrarRequest();
    errores?: string[];

    constructor(private authService: AuthService, private router: Router) {}

    ngOnInit(): void {}

    onSubmit() {
        this.authService
            .register(this.registrarRequest)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    if (error.status === HttpStatusCode.BadRequest) {
                        this.errores = (
                            error.error as RegistrarUsuarioResponse
                        ).errores;
                    }
                    return throwError(() => error);
                })
            )
            .subscribe((response) => {
                if (!response.errores) {
                    this.router.navigate(['/login']);
                } else {
                    alert(response.message);
                }
            });
    }
}

export const newRegistrarRequest = (): RegistrarUsuarioRequest => {
    return {
        username: '',
        password: '',
        idSolicitud: 0,
    };
};
