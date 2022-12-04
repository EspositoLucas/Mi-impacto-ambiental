import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
    newOrganizacion,
    Organizacion,
} from 'src/app/models/organizacion.model';
import { OrganizacionJsonService } from '../organizacion-json.service';

@Component({
    selector: 'app-crear-organizacion',
    templateUrl: '../organizacion-details.html',
    styleUrls: ['./crear-organizacion.component.css'],
})
export class CrearOrganizacionComponent implements OnInit {
    organizacion: Organizacion;

    constructor(
        private organizacionService: OrganizacionJsonService,
        private router: Router
    ) {
        this.organizacion = newOrganizacion();
    }

    ngOnInit(): void {}

    onSubmit() {
        this.organizacionService.create(this.organizacion).subscribe(() => {
            this.router.navigate(['/organizacion']);
        });
    }
}
