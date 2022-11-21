import { Injectable } from '@angular/core';
import { ApiHelperService } from '../api/api-helper.service';
import { BaseService } from '../base/base.service';
import { Sector } from '../models/sector.model';

@Injectable({
    providedIn: 'root',
})
export class SectorService extends BaseService<Sector> {
    constructor(apiHelperService: ApiHelperService) {
        super(apiHelperService, 'sector');
    }
}
