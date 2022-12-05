import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-index-miembro-container',
    templateUrl: './index-miembro.container.html',
    styleUrls: ['./index-miembro.container.css'],
})
export class IndexMiembroContainer implements OnInit {
    clienteElegido = '';

    constructor() {}

    ngOnInit(): void {}
}
