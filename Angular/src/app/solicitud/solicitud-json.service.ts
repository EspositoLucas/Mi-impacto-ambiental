import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import {
    ApiJsonHelperService,
    BaseResponse,
} from '../api/api-json-helper.service';
import { BaseJsonService } from '../base/base-json.service';
import { Solicitud } from '../models/solicitud.model';

@Injectable({
    providedIn: 'root',
})
export class SolicitudJsonService extends BaseJsonService<Solicitud> {
    constructor(apiHelperService: ApiJsonHelperService) {
        super(apiHelperService, 'organizacion/solicitudes');
    }

    public aceptarSolicitud(idSolicitud: number): Observable<BaseResponse> {
        const request = { idSolicitud };
        return this.apiHelperService
            .post('organizacion/aceptar-solicitud', request)
            .pipe(map((response) => response.body!));
    }
}
