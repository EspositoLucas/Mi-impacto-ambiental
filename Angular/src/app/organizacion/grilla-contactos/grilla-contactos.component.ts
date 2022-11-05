import { Component, Input, OnInit } from '@angular/core';
import { newContacto } from 'src/app/models/contacto.model';
import { Organizacion } from 'src/app/models/organizacion.model';

@Component({
    selector: 'app-grilla-contactos',
    templateUrl: './grilla-contactos.component.html',
    styleUrls: ['./grilla-contactos.component.css'],
})
export class GrillaContactosComponent implements OnInit {
    @Input() organizacion!: Organizacion;

    constructor() {}

    ngOnInit(): void {}

    borrarContacto(index: number) {
        this.organizacion.contactos.splice(index, 1);
    }

    agregarContacto() {
        this.organizacion.contactos.push(newContacto());
    }
}
