import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { ApiHelperService, BaseResponse } from '../api/api-helper.service';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class AuthApiService {
    constructor(private apiHelperService: ApiHelperService) {}

    login(request: LoginRequest): Observable<HttpResponse<LoginResponse>> {
        return this.apiHelperService.post<LoginResponse>('auth/login', request);
    }
}

export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResponse extends BaseResponse {
    accessToken: string;
}
