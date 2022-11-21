import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { ApiHelperService, BaseResponse } from '../api/api-helper.service';
import { BaseService } from '../base/base.service';
import { Solicitud } from '../models/solicitud.model';

@Injectable({
    providedIn: 'root',
})
export class SolicitudService extends BaseService<Solicitud> {
    constructor(apiHelperService: ApiHelperService) {
        super(apiHelperService, 'organizacion/solicitudes');
    }

    public aceptarSolicitud(idSolicitud: number): Observable<BaseResponse> {
        const request = { idSolicitud };
        return this.apiHelperService
            .post('organizacion/aceptar-solicitud', request)
            .pipe(map((response) => response.body!));
    }
}
