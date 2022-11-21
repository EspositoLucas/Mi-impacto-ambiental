import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { newIdTextPair } from 'src/app/models/idtextpair.model';
import { TipoDocumentoService } from 'src/app/tipo-documento/tipo-documento.service';
import { BaseSelectComponent } from '../base-select/base-select.component';

@Component({
    selector: 'app-tipo-documento-select',
    templateUrl: '../base-select/base-select.component.html',
    styleUrls: ['./tipo-documento-select.component.css'],
})
export class TipoDocumentoSelectComponent
    extends BaseSelectComponent
    implements OnInit
{
    constructor(private tipoDocumentoService: TipoDocumentoService) {
        super();
    }

    ngOnInit(): void {
        this.loadItems(
            this.tipoDocumentoService.getAll().pipe(
                map((tipos) => {
                    return tipos.map((tipo) =>
                        newIdTextPair({
                            id: tipo.id,
                            text: tipo.text,
                        })
                    );
                })
            )
        );
    }
}
