import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Sector } from 'src/app/models/sector.model';
import { BaseIndexComponent } from 'src/app/templates/base-index/base-index.component';
import { SectorService } from '../sector.service';

@Component({
    selector: 'app-index-sector',
    templateUrl: './index-sector.component.html',
    styleUrls: ['./index-sector.component.css'],
})
export class IndexSectorComponent
    extends BaseIndexComponent<Sector>
    implements OnInit
{
    constructor(router: Router, service: SectorService) {
        super(router, 'sector', service);
    }

    ngOnInit(): void {
        super.onInit();
    }
}
