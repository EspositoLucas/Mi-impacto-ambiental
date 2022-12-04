import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import {
    ApiJsonHelperService,
    BaseResponse,
} from '../api/api-json-helper.service';
import { BaseJsonService } from '../base/base-json.service';
import { Miembro } from '../models/miembro.model';

@Injectable({
    providedIn: 'root',
})
export class MiembroJsonService extends BaseJsonService<Miembro> {
    constructor(apiHelperService: ApiJsonHelperService) {
        super(apiHelperService, 'miembro');
    }

    override create(obj: Miembro): Observable<CrearMiembroResponse | null> {
        return super
            .create(obj)
            .pipe(map((response) => response as CrearMiembroResponse));
    }
}

interface CrearMiembroResponse extends BaseResponse {
    idSolicitud: number;
}
