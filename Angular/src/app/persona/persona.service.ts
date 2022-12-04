import { Injectable } from '@angular/core';
import { ApiJsonHelperService } from '../api/api-json-helper.service';
import { BaseJsonService } from '../base/base-json.service';
import { Persona } from '../models/persona.model';

@Injectable({
    providedIn: 'root',
})
export class PersonaService extends BaseJsonService<Persona> {
    constructor(apiHelperService: ApiJsonHelperService) {
        super(apiHelperService, 'persona');
    }
}
