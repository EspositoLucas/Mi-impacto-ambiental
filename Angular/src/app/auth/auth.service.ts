import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';
import { AuthApiService } from './authapi.service';
import { User } from '../models/user.model';

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    loggedUser?: User;
    isAuthenticated = false;

    constructor(private authApiService: AuthApiService) {}

    login(
        username: string,
        password: string
    ): Observable<{ success: boolean; errorMessage?: string }> {
        const request = { username, password };
        return this.authApiService.login(request).pipe(
            tap((httpResponse) => {
                if (httpResponse.ok) {
                    this.loggedUser = {
                        username,
                        accessToken: httpResponse.body!.accessToken,
                    };
                    this.isAuthenticated = true;
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
}
