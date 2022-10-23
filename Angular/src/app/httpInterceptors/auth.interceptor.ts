import { Injectable } from '@angular/core';
import {
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private authService: AuthService) {}

    intercept(
        req: HttpRequest<any>,
        next: HttpHandler
    ): Observable<HttpEvent<any>> {
        if (this.authService.isAuthenticated) {
            const headers = req.headers.set(
                'Authorization',
                `Bearer ${this.authService.loggedUser!.accessToken}`
            );
            req = req.clone({ headers });
        }
        return next.handle(req);
    }
}
