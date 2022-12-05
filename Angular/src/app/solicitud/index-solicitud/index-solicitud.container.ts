import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { SolicitudHtmlService } from '../solicitud-html.service';

@Component({
    selector: 'app-index-solicitud',
    templateUrl: './index-solicitud.container.html',
    styleUrls: ['./index-solicitud.container.css'],
})
export class IndexSolicitudContainer implements OnInit {
    clienteElegido = '';
    html$!: Observable<string>;

    constructor(private solicitudHtmlService: SolicitudHtmlService) {}

    ngOnInit(): void {
        this.html$ = this.solicitudHtmlService.getAll();
    }
}
