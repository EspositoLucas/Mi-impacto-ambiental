import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, startWith, Subject, switchMap, tap } from 'rxjs';
import { cantidadFormatTextMessage } from 'src/app/models/cantidad.model';
import { Organizacion } from 'src/app/models/organizacion.model';
import { BaseIndexComponent } from 'src/app/templates/base-index/base-index.component';
import { OrganizacionService } from '../organizacion.service';

@Component({
    selector: 'app-index-organizacion',
    templateUrl: './index-organizacion.component.html',
    styleUrls: ['./index-organizacion.component.css'],
})
export class IndexOrganizacionComponent
    extends BaseIndexComponent
    implements OnInit
{
    organizacionesSubject$ = new Subject<void>();
    organizaciones$!: Observable<Organizacion[]>;

    constructor(
        private organizacionService: OrganizacionService,
        router: Router
    ) {
        super(router, 'organizacion');
    }

    ngOnInit(): void {
        this.organizaciones$ = this.organizacionesSubject$.asObservable().pipe(
            startWith(null),
            switchMap(() => this.organizacionService.getAll()),
            tap((organizaciones) => {
                organizaciones.forEach((organizacion) =>
                    cantidadFormatTextMessage(organizacion.factorK)
                );
            })
        );
    }

    deleteOrganizacion(id: number) {
        console.log(`Borrando organizacion ${id}`);
        this.organizacionesSubject$.next();
    }
}
