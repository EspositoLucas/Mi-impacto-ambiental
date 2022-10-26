import { Injectable } from '@angular/core';
import { map, Observable, of } from 'rxjs';
import { ApiHelperService } from '../api/api-helper.service';
import { BaseService } from '../base/base.service';
import { newCantidadComoIdTextPair } from '../models/cantidad.model';
import { newIdTextPair } from '../models/idtextpair.model';
import { newOrganizacion, Organizacion } from '../models/organizacion.model';

@Injectable({
    providedIn: 'root',
})
export class OrganizacionService extends BaseService<Organizacion> {
    constructor(apiHelperService: ApiHelperService) {
        super(apiHelperService, 'organizacion');
    }

    override getAll(): Observable<Organizacion[]> {
        console.log('Buscando Organizaciones a la API...');
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

    override getById(id: number): Observable<Organizacion> {
        return this.getAll().pipe(
            map(
                (organizaciones) =>
                    organizaciones.find(
                        (organizacion) => organizacion.id === id
                    )!
            )
        );
    }

    override delete(id: number): Observable<void> {
        return of(undefined);
    }
}
