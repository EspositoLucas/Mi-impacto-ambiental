import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ApiHelperService } from '../api/api-helper.service';
import { BaseService } from '../base/base.service';
import { IdTextPair, newIdTextPair } from '../models/idtextpair.model';

@Injectable({
    providedIn: 'root',
})
export class TipoOrganizacionService extends BaseService<IdTextPair> {
    constructor(apiHelperService: ApiHelperService) {
        super(apiHelperService, 'organizacion/tipos');
    }
}
