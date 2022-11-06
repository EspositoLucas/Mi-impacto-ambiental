import { Injectable } from '@angular/core';
import {
    HttpClient,
    HttpErrorResponse,
    HttpHeaders,
    HttpResponse,
    HttpStatusCode,
} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError, EMPTY, Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Injectable({
    providedIn: 'root',
})
export class ApiHelperService {
    constructor(
        protected httpClient: HttpClient,
        protected toastr: ToastrService
    ) {}

    post<TResponse extends BaseResponse>(
        url: string,
        body: any,
        headers?: HttpHeaders
    ): Observable<HttpResponse<TResponse>> {
        const apiUrl = this.getUrl(url);
        const options: {
            headers?: HttpHeaders;
            observe: 'response';
        } = { headers, observe: 'response' };
        const response$ = this.httpClient.post<TResponse>(
            apiUrl,
            body,
            options
        );
        return this.showToastMessage(response$);
    }

    get<TResponse extends BaseResponse>(
        url: string,
        headers?: HttpHeaders
    ): Observable<HttpResponse<TResponse>> {
        const apiUrl = this.getUrl(url);
        const options: {
            headers?: HttpHeaders;
            observe: 'response';
        } = { headers, observe: 'response' };
        const response$ = this.httpClient.get<TResponse>(apiUrl, options);
        return this.showToastMessage(response$);
    }

    put<TResponse extends BaseResponse>(
        url: string,
        body: any,
        headers?: HttpHeaders
    ): Observable<HttpResponse<TResponse>> {
        const apiUrl = this.getUrl(url);
        const options: {
            headers?: HttpHeaders;
            observe: 'response';
        } = { headers, observe: 'response' };
        const response$ = this.httpClient.put<TResponse>(apiUrl, body, options);
        return this.showToastMessage(response$);
    }

    delete<TResponse extends BaseResponse>(
        url: string,
        headers?: HttpHeaders
    ): Observable<HttpResponse<TResponse>> {
        const apiUrl = this.getUrl(url);
        const options: {
            headers?: HttpHeaders;
            observe: 'response';
        } = { headers, observe: 'response' };
        const response$ = this.httpClient.delete<TResponse>(apiUrl, options);
        return this.showToastMessage(response$);
    }

    private getUrl(url: string): string {
        return `${environment.baseApiUrl}/api/${url}`;
    }

    private showToastMessage<TResponse>(
        request: Observable<HttpResponse<TResponse>>
    ): Observable<HttpResponse<TResponse>> {
        return request.pipe(
            catchError((err: HttpErrorResponse) => {
                if (err.status === HttpStatusCode.Unauthorized) {
                    this.toastr.error('No estas logeado!');
                } else if (err.status === HttpStatusCode.Forbidden) {
                    this.toastr.error(
                        'No tenes los permisos suficientes para realizar esta operacion'
                    );
                }
                return EMPTY;
            })
        );
    }
}

export interface BaseResponse {
    message: string;
}

export interface ResponseWithResults<TResult> extends BaseResponse {
    results: TResult[];
}

export interface ResponseWithSingleResult<TResult> extends BaseResponse {
    result: TResult;
}
