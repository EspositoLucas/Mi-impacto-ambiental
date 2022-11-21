import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
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
        return this.httpClient.post<TResponse>(apiUrl, body, options);
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
        return this.httpClient.get<TResponse>(apiUrl, options);
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
        return this.httpClient.put<TResponse>(apiUrl, body, options);
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
        return this.httpClient.delete<TResponse>(apiUrl, options);
    }

    private getUrl(url: string): string {
        return `${environment.baseApiUrl}/api/${url}`;
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
