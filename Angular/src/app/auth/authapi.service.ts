import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
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

    register(
        request: RegistrarUsuarioRequest
    ): Observable<HttpResponse<RegistrarUsuarioResponse>> {
        return this.apiHelperService.post<RegistrarUsuarioResponse>(
            'auth/register',
            request
        );
    }
}

export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResponse extends BaseResponse {
    accessToken: string;
}

export interface RegistrarUsuarioRequest {
    username: string;
    password: string;
    idSolicitud: number;
}

export interface RegistrarUsuarioResponse extends BaseResponse {
    errores: string[];
}
