import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Organizacion } from 'src/app/models/organizacion.model';
import { BaseEditComponent } from 'src/app/templates/base-edit/base-edit.component';
import { OrganizacionService } from '../organizacion.service';

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

    constructor(route: ActivatedRoute, service: OrganizacionService) {
        super(route, service);
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
