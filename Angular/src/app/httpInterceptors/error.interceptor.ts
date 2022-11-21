import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
    HttpErrorResponse,
    HttpStatusCode,
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { BaseResponse } from '../api/api-helper.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private toastr: ToastrService) {}

    intercept(
        request: HttpRequest<unknown>,
        next: HttpHandler
    ): Observable<HttpEvent<unknown>> {
        return next.handle(request).pipe(
            catchError((error: HttpErrorResponse) => {
                if (error.error instanceof Error) {
                    // A client-side or network error occurred. Handle it accordingly.
                    console.error('Ocurrio un error:', error.error.message);
                } else {
                    // The backend returned an unsuccessful response code.
                    // The response body may contain clues as to what went wrong,
                    console.error(
                        `Backend returned code ${error.status}, body was: ${error.error}`
                    );

                    if (error.status === HttpStatusCode.Unauthorized) {
                        this.toastr.error('No estas logeado!');
                    } else if (error.status === HttpStatusCode.Forbidden) {
                        this.toastr.error(
                            'No tenes los permisos suficientes para realizar esta operacion'
                        );
                    } else {
                        const response = error.error as BaseResponse;
                        if (response.message)
                            this.toastr.error(response.message);
                    }
                }

                return throwError(() => error);
            })
        );
    }
}
