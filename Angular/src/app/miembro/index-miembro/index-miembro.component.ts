import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Miembro } from 'src/app/models/miembro.model';
import { BaseIndexComponent } from 'src/app/templates/base-index/base-index.component';
import { MiembroService } from '../miembro.service';

@Component({
    selector: 'app-index-miembro',
    templateUrl: './index-miembro.component.html',
    styleUrls: ['./index-miembro.component.css'],
})
export class IndexMiembroComponent
    extends BaseIndexComponent<Miembro>
    implements OnInit
{
    constructor(router: Router, service: MiembroService) {
        super(router, 'miembro', service);
    }

    ngOnInit(): void {
        super.onInit();
    }
}
