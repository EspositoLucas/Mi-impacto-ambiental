import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ApiService } from '../api/api.service';
import { IdTextPair } from '../models/idtextpair.model';

@Injectable({
    providedIn: 'root',
})
export class TipoOrganizacionService extends ApiService {
    constructor(httpClient: HttpClient) {
        super(httpClient);
    }

    getTiposOrganizacion(): Observable<IdTextPair[]> {
        // TODO: implementacion usando la API
        const mockData = [
            {
                id: 1,
                text: 'Text: 1',
            },
            {
                id: 2,
                text: 'Text: 2',
            },
        ];
        return of(mockData);
    }
}
