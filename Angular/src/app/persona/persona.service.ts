import { Injectable } from '@angular/core';
import { ApiHelperService } from '../api/api-helper.service';
import { BaseService } from '../base/base.service';
import { Persona } from '../models/persona.model';

@Injectable({
    providedIn: 'root',
})
export class PersonaService extends BaseService<Persona> {
    constructor(apiHelperService: ApiHelperService) {
        super(apiHelperService, 'persona');
    }
}
