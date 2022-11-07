import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiHelperService, BaseResponse } from '../api/api-helper.service';
import { BaseService } from '../base/base.service';
import { CargarMedicionesRequest } from '../cargar-mediciones/cargar-mediciones.component';
import { Organizacion } from '../models/organizacion.model';

@Injectable({
    providedIn: 'root',
})
export class OrganizacionService extends BaseService<Organizacion> {
    constructor(apiHelperService: ApiHelperService) {
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
