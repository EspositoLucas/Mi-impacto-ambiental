import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { ApiService, ResponseWithResults } from '../api/api.service';
import { IdTextPair } from '../models/idtextpair.model';

@Injectable({
    providedIn: 'root',
})
export class TipoOrganizacionService extends ApiService {
    constructor(httpClient: HttpClient) {
        super(httpClient);
    }

    getTiposOrganizacion(): Observable<IdTextPair[]> {
        return this.get<GetTiposDeOrganizacionResponse>(
            'organizacion/tipos'
        ).pipe(map((httpResponse) => httpResponse.body!.results));
    }
}

export interface GetTiposDeOrganizacionResponse
    extends ResponseWithResults<IdTextPair> {}
