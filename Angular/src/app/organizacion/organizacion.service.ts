import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
    ApiJsonHelperService,
    BaseResponse,
} from '../api/api-json-helper.service';
import { BaseJsonService } from '../base/base-json.service';
import { CargarMedicionesRequest } from '../cargar-mediciones/cargar-mediciones.component';
import { Organizacion } from '../models/organizacion.model';

@Injectable({
    providedIn: 'root',
})
export class OrganizacionService extends BaseJsonService<Organizacion> {
    constructor(apiHelperService: ApiJsonHelperService) {
        super(apiHelperService, 'organizacion');
    }

    cargarMediciones(
        request: CargarMedicionesRequest
    ): Observable<HttpResponse<BaseResponse>> {
        const formData = new FormData();
        formData.append('idOrganizacion', request.organizacion.id.toString());
        formData.append('file', request.file);
        return this.apiHelperService.post(
            'organizacion/cargar-mediciones',
            formData
        );
    }
}
