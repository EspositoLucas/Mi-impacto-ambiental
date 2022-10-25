import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, of } from 'rxjs';
import { ApiService, ResponseWithResults } from '../api/api.service';
import { IdTextPair, newIdTextPair } from '../models/idtextpair.model';

@Injectable({
    providedIn: 'root',
})
export class TipoOrganizacionService extends ApiService {
    constructor(httpClient: HttpClient) {
        super(httpClient);
    }

    getTiposOrganizacion(): Observable<IdTextPair[]> {
        // return this.get<GetTiposDeOrganizacionResponse>(
        //     'organizacion/tipos'
        // ).pipe(map((httpResponse) => httpResponse.body!.results));
        return of([
            newIdTextPair({ id: 1, text: 'Tipo 1' }),
            newIdTextPair({ id: 2, text: 'Tipo 2' }),
        ]);
    }
}

export interface GetTiposDeOrganizacionResponse
    extends ResponseWithResults<IdTextPair> {}
