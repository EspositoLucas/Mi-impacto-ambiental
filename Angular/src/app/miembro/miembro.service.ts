import { Injectable } from '@angular/core';
import { basePlacements } from '@popperjs/core';
import { map, Observable } from 'rxjs';
import { ApiHelperService, BaseResponse } from '../api/api-helper.service';
import { BaseService } from '../base/base.service';
import { Miembro } from '../models/miembro.model';

@Injectable({
    providedIn: 'root',
})
export class MiembroService extends BaseService<Miembro> {
    constructor(apiHelperService: ApiHelperService) {
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
