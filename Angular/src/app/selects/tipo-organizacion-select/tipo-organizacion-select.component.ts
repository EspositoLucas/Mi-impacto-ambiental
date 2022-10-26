import { Component, OnInit } from '@angular/core';
import { TipoOrganizacionService } from 'src/app/tipo-organizacion/tipo-organizacion.service';
import { BaseSelectComponent } from '../base-select/base-select.component';

@Component({
    selector: 'app-tipo-organizacion-select',
    templateUrl: '../base-select/base-select.component.html',
    styleUrls: ['./tipo-organizacion-select.component.css'],
})
export class TipoOrganizacionSelectComponent
    extends BaseSelectComponent
    implements OnInit
{
    constructor(private tipoOrganizacionService: TipoOrganizacionService) {
        super(tipoOrganizacionService.getAll());
    }

    override ngOnInit(): void {
        super.ngOnInit();
    }
}
