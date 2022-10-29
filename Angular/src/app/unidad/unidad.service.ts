import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ApiHelperService } from '../api/api-helper.service';
import { BaseService } from '../base/base.service';
import { newUnidad, Unidad } from '../models/unidad.model';

@Injectable({
    providedIn: 'root',
})
export class UnidadService extends BaseService<Unidad> {
    constructor(apiHelperService: ApiHelperService) {
        super(apiHelperService, 'unidad');
    }
}
