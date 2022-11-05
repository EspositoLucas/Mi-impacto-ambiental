import { Component, OnInit } from '@angular/core';
import { ClasificacionOrganizacionService } from 'src/app/clasificacion-organizacion/clasificacion-organizacion.service';
import { BaseSelectComponent } from '../base-select/base-select.component';

@Component({
    selector: 'app-clasificacion-organizacion-select',
    templateUrl: '../base-select/base-select.component.html',
    styleUrls: ['./clasificacion-organizacion-select.component.css'],
})
export class ClasificacionOrganizacionSelectComponent
    extends BaseSelectComponent
    implements OnInit
{
    constructor(
        private clasificacionOrganizacionService: ClasificacionOrganizacionService
    ) {
        super();
    }

    ngOnInit(): void {
        this.loadItems(this.clasificacionOrganizacionService.getAll());
    }
}
