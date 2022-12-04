import { Injectable } from '@angular/core';
import { ApiJsonHelperService } from '../api/api-json-helper.service';
import { BaseJsonService } from '../base/base-json.service';
import { IdTextPair } from '../models/idtextpair.model';

@Injectable({
    providedIn: 'root',
})
export class ClasificacionOrganizacionService extends BaseJsonService<IdTextPair> {
    constructor(apiHelperService: ApiJsonHelperService) {
        super(apiHelperService, 'organizacion/clasificaciones');
    }
}
