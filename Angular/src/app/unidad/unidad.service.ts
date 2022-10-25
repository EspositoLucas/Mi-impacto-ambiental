import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ApiService, ResponseWithResults } from '../api/api.service';
import { IdTextPair } from '../models/idtextpair.model';
import { newUnidad, Unidad } from '../models/unidad.model';

@Injectable({
    providedIn: 'root',
})
export class UnidadService extends ApiService {
    constructor(httpClient: HttpClient) {
        super(httpClient);
    }

    getUnidades(): Observable<Unidad[]> {
        // return this.get<GetUnidadesResponse>(
        //     'unidad'
        // ).pipe(map((httpResponse) => httpResponse.body!.results));
        return of([
            newUnidad({ id: 1, simbolo: 'KG' }),
            newUnidad({ id: 2, simbolo: 'G' }),
        ]);
    }
}

export interface GetUnidadesResponse extends ResponseWithResults<IdTextPair> {}
