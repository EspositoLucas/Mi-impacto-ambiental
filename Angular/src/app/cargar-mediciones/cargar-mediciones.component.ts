import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { IdTextPair } from '../models/idtextpair.model';
import { OrganizacionJsonService } from '../organizacion/organizacion-json.service';

@Component({
    selector: 'app-cargar-mediciones',
    templateUrl: './cargar-mediciones.component.html',
    styleUrls: ['./cargar-mediciones.component.css'],
})
export class CargarMedicionesComponent implements OnInit {
    request: CargarMedicionesRequest = {} as CargarMedicionesRequest;

    constructor(
        private organizacionService: OrganizacionJsonService,
        private router: Router,
        private toastr: ToastrService
    ) {}

    ngOnInit(): void {}

    uploadFile(event: any) {
        this.request.file = event.target.files[0];
    }

    onSubmit() {
        this.request.idOrganizacion = this.request.organizacion.id;
        this.organizacionService
            .cargarMediciones(this.request)
            .subscribe((response) => {
                this.toastr.success('Mediciones cargadas correctamente');
                this.router.navigate(['/organizacion']);
            });
    }
}

export interface CargarMedicionesRequest {
    organizacion: IdTextPair;
    idOrganizacion: number;
    file: File;
}
