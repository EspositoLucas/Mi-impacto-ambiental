import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Solicitud } from 'src/app/models/solicitud.model';
import { BaseIndexComponent } from 'src/app/templates/base-index/base-index.component';
import { SolicitudJsonService } from '../../solicitud-json.service';

@Component({
    selector: 'app-index-solicitud-pesado',
    templateUrl: './index-solicitud-pesado.component.html',
    styleUrls: ['./index-solicitud-pesado.component.css'],
})
export class IndexSolicitudPesadoComponent
    extends BaseIndexComponent<Solicitud>
    implements OnInit
{
    constructor(
        router: Router,
        private solicitudService: SolicitudJsonService,
        private toastr: ToastrService
    ) {
        super(router, 'solicitud', solicitudService);
    }

    ngOnInit(): void {
        super.onInit();
    }

    onAccept(solicitud: Solicitud) {
        this.solicitudService.aceptarSolicitud(solicitud.id).subscribe(() => {
            this.toastr.success('Solicitud aceptada correctamente');
            this.resultsSubject$.next();
        });
    }
}
