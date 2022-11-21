import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { newIdTextPair } from 'src/app/models/idtextpair.model';
import { SectorService } from 'src/app/sector/sector.service';
import { BaseSelectComponent } from '../base-select/base-select.component';

@Component({
    selector: 'app-sector-select',
    templateUrl: '../base-select/base-select.component.html',
    styleUrls: ['./sector-select.component.css'],
})
export class SectorSelectComponent
    extends BaseSelectComponent
    implements OnInit
{
    constructor(private sectorService: SectorService) {
        super();
    }

    ngOnInit(): void {
        this.loadItems(
            this.sectorService.getAllIdTextPairs().pipe(
                map((sectores) => {
                    return sectores.map((sector) =>
                        newIdTextPair({
                            id: sector.id,
                            text: sector.text,
                        })
                    );
                })
            )
        );
    }
}
