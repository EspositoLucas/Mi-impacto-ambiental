import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';
import {
    AuthApiService,
    LoginResponse,
    RegistrarUsuarioRequest,
    RegistrarUsuarioResponse,
} from './authapi.service';
import { User } from '../models/user.model';

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    loggedUser?: User;
    isAuthenticated = false;

    constructor(private authApiService: AuthApiService) {
        const jsonUser = localStorage.getItem('user');
        if (jsonUser) {
            this.loggedUser = JSON.parse(jsonUser);
            this.isAuthenticated = true;
        }
    }

    login(
        username: string,
        password: string
    ): Observable<{ success: boolean; errorMessage?: string }> {
        const request = { username, password };
        return this.authApiService.login(request).pipe(
            tap((httpResponse) => {
                if (httpResponse.ok) {
                    this.loginSuccessful(username, httpResponse.body!);
                    this.saveUserToLocalStorage();
                }
            }),
            map((httpResponse) => {
                if (!httpResponse.ok) {
                    return {
                        success: false,
                        errorMessage:
                            httpResponse.body?.message ??
                            `Error ${httpResponse.status}`,
                    };
                }
                return {
                    success: true,
                    errorMessage: undefined,
                };
            })
        );
    }

    register(
        request: RegistrarUsuarioRequest
    ): Observable<RegistrarUsuarioResponse> {
        return this.authApiService
            .register(request)
            .pipe(map((response) => response.body!));
    }

    logout() {
        this.isAuthenticated = false;
        this.loggedUser = undefined;
        localStorage.removeItem('user');
    }

    private loginSuccessful(username: string, loginResponse: LoginResponse) {
        this.loggedUser = {
            username,
            accessToken: loginResponse.accessToken,
        };
        this.isAuthenticated = true;
    }

    private saveUserToLocalStorage() {
        localStorage.setItem('user', JSON.stringify(this.loggedUser));
    }
}
