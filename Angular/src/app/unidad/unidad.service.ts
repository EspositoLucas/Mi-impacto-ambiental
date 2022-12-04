import { Injectable } from '@angular/core';
import { ApiJsonHelperService } from '../api/api-json-helper.service';
import { BaseJsonService } from '../base/base-json.service';
import { Unidad } from '../models/unidad.model';

@Injectable({
    providedIn: 'root',
})
export class UnidadService extends BaseJsonService<Unidad> {
    constructor(apiHelperService: ApiJsonHelperService) {
        super(apiHelperService, 'unidad');
    }
}
