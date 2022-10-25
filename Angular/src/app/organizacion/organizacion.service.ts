import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, of } from 'rxjs';
import { ApiService, ResponseWithResults } from '../api/api.service';
import { newCantidadComoIdTextPair } from '../models/cantidad.model';
import { IdTextPair, newIdTextPair } from '../models/idtextpair.model';
import { newOrganizacion, Organizacion } from '../models/organizacion.model';

@Injectable({
    providedIn: 'root',
})
export class OrganizacionService extends ApiService {
    constructor(httpClient: HttpClient) {
        super(httpClient);
    }

    getAllOrganizaciones(): Observable<Organizacion[]> {
        // return this.get<GetTiposDeOrganizacionResponse>(
        //     'organizacion/tipos'
        // ).pipe(map((httpResponse) => httpResponse.body!.results));
        return of([
            newOrganizacion({
                id: 1,
                razonSocial: 'Org 1',
                tipoOrganizacion: newIdTextPair({ id: 1, text: 'TipoOrg 1' }),
                clasificacion: newIdTextPair({ id: 1, text: 'Clasif 1' }),
                cantDiasHabilesPorSemana: 5,
                factorK: newCantidadComoIdTextPair({
                    valor: 10,
                    unidad: newIdTextPair({ id: 1, text: 'Unid 1' }),
                }),
            }),
            newOrganizacion({
                id: 2,
                razonSocial: 'Org 2',
                tipoOrganizacion: newIdTextPair({ id: 2, text: 'TipoOrg 2' }),
                clasificacion: newIdTextPair({ id: 2, text: 'Clasif 2' }),
                cantDiasHabilesPorSemana: 4,
                factorK: newCantidadComoIdTextPair({
                    valor: 20,
                    unidad: newIdTextPair({ id: 2, text: 'Unid 2' }),
                }),
            }),
        ]);
    }
}

export interface GetTiposDeOrganizacionResponse
    extends ResponseWithResults<IdTextPair> {}
