import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { tap } from 'rxjs';
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
    extends BaseIndexComponent<Organizacion>
    implements OnInit
{
    constructor(router: Router, service: OrganizacionService) {
        super(router, 'organizacion', service);
    }

    ngOnInit(): void {
        super.onInit();
        this.results$ = this.results$.pipe(
            tap((organizaciones) => {
                organizaciones.forEach((organizacion) =>
                    cantidadFormatTextMessage(organizacion.factorK)
                );
            })
        );
    }

    onDelete(organizacion: Organizacion) {
        const message = `"Organizacion ${organizacion.razonSocial}"`;
        super.deleteEntidad(organizacion, message);
    }
}
