import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Organizacion } from 'src/app/models/organizacion.model';
import { BaseEditComponent } from 'src/app/templates/base-edit/base-edit.component';
import { OrganizacionJsonService } from '../organizacion-json.service';

@Component({
    selector: 'app-edit-organizacion',
    templateUrl: '../organizacion-details.html',
    styleUrls: ['./edit-organizacion.component.css'],
})
export class EditOrganizacionComponent
    extends BaseEditComponent<Organizacion>
    implements OnInit
{
    organizacion!: Organizacion;

    constructor(
        route: ActivatedRoute,
        router: Router,
        service: OrganizacionJsonService
    ) {
        super(route, router, 'organizacion', service);
    }

    ngOnInit(): void {
        this.loadEntity();
    }

    get entity(): Organizacion {
        return this.organizacion;
    }

    set entity(organizacion: Organizacion) {
        this.organizacion = organizacion;
    }
}
