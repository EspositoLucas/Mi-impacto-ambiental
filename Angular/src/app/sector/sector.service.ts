import { Injectable } from '@angular/core';
import { ApiJsonHelperService } from '../api/api-json-helper.service';
import { BaseJsonService } from '../base/base-json.service';
import { Sector } from '../models/sector.model';

@Injectable({
    providedIn: 'root',
})
export class SectorService extends BaseJsonService<Sector> {
    constructor(apiHelperService: ApiJsonHelperService) {
        super(apiHelperService, 'sector');
    }
}
