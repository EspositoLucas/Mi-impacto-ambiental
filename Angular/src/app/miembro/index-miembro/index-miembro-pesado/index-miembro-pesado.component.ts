import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Miembro } from 'src/app/models/miembro.model';
import { BaseIndexComponent } from 'src/app/templates/base-index/base-index.component';
import { MiembroJsonService } from '../../miembro-json.service';

@Component({
    selector: 'app-index-miembro-pesado',
    templateUrl: './index-miembro-pesado.component.html',
    styleUrls: ['./index-miembro-pesado.component.css'],
})
export class IndexMiembroPesadoComponent
    extends BaseIndexComponent<Miembro>
    implements OnInit
{
    constructor(router: Router, service: MiembroJsonService) {
        super(router, 'miembro', service);
    }

    ngOnInit(): void {
        super.onInit();
    }
}
