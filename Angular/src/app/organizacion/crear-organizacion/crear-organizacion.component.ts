import { Component, OnInit } from '@angular/core';
import {
    newOrganizacion,
    Organizacion,
} from 'src/app/models/organizacion.model';

@Component({
    selector: 'app-crear-organizacion',
    templateUrl: './crear-organizacion.component.html',
    styleUrls: ['./crear-organizacion.component.css'],
})
export class CrearOrganizacionComponent implements OnInit {
    organizacion: Organizacion;

    constructor() {
        this.organizacion = newOrganizacion();
    }

    ngOnInit(): void {}

    onSubmit() {
        console.log(this.organizacion);
    }
}
