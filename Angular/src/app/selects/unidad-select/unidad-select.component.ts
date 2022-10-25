import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { newIdTextPair } from 'src/app/models/idtextpair.model';
import { UnidadService } from 'src/app/unidad/unidad.service';
import { BaseSelectComponent } from '../base-select/base-select.component';

@Component({
    selector: 'app-unidad-select',
    templateUrl: '../base-select/base-select.component.html',
    styleUrls: ['./unidad-select.component.css'],
})
export class UnidadSelectComponent
    extends BaseSelectComponent
    implements OnInit
{
    constructor(private unidadService: UnidadService) {
        super(
            unidadService.getUnidades().pipe(
                map((results) =>
                    results.map((unidad) =>
                        newIdTextPair({
                            id: unidad.id!,
                            text: unidad.simbolo,
                        })
                    )
                )
            )
        );
    }

    override ngOnInit(): void {
        super.ngOnInit();
    }
}
