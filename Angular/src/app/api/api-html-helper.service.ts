import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class ApiHtmlHelperService {
    constructor(protected httpClient: HttpClient) {}

    get(url: string, headers?: HttpHeaders): Observable<HttpResponse<string>> {
        const apiUrl = this.getUrl(url);
        return this.httpClient.get(apiUrl, {
            observe: 'response',
            responseType: 'text',
        });
    }

    private getUrl(url: string): string {
        return `${environment.baseApiUrl}/liviano/${url}`;
    }
}
