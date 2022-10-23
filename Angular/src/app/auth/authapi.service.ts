import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { ApiService, BaseResponse } from '../api/api.service';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class AuthApiService extends ApiService {
    constructor(httpClient: HttpClient) {
        super(httpClient);
    }

    login(request: LoginRequest): Observable<HttpResponse<LoginResponse>> {
        return this.post<LoginResponse>('auth/login', request);
    }
}

export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResponse extends BaseResponse {
    accessToken: string;
}
