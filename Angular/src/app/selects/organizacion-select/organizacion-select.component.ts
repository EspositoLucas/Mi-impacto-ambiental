import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { newIdTextPair } from 'src/app/models/idtextpair.model';
import { OrganizacionJsonService } from 'src/app/organizacion/organizacion-json.service';
import { BaseSelectComponent } from '../base-select/base-select.component';

@Component({
    selector: 'app-organizacion-select',
    templateUrl: '../base-select/base-select.component.html',
    styleUrls: ['./organizacion-select.component.css'],
})
export class OrganizacionSelectComponent
    extends BaseSelectComponent
    implements OnInit
{
    constructor(private organizacionService: OrganizacionJsonService) {
        super();
    }

    ngOnInit(): void {
        this.loadItems(
            this.organizacionService.getAll().pipe(
                map((organizaciones) => {
                    return organizaciones.map((organizacion) =>
                        newIdTextPair({
                            id: organizacion.id,
                            text: organizacion.razonSocial,
                        })
                    );
                })
            )
        );
    }
}
