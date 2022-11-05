import { Injectable } from '@angular/core';
import { ApiHelperService } from '../api/api-helper.service';
import { BaseService } from '../base/base.service';
import { Organizacion } from '../models/organizacion.model';

@Injectable({
    providedIn: 'root',
})
export class OrganizacionService extends BaseService<Organizacion> {
    constructor(apiHelperService: ApiHelperService) {
        super(apiHelperService, 'organizacion');
    }
}
