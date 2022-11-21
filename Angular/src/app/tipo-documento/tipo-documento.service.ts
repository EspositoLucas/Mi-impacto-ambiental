import { Injectable } from '@angular/core';
import { ApiHelperService } from '../api/api-helper.service';
import { BaseService } from '../base/base.service';
import { IdTextPair } from '../models/idtextpair.model';

@Injectable({
    providedIn: 'root',
})
export class TipoDocumentoService extends BaseService<IdTextPair> {
    constructor(apiHelperService: ApiHelperService) {
        super(apiHelperService, 'persona/tipos-documento');
    }
}
