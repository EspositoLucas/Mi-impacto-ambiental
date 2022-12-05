import { Component, OnInit } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { MiembroHtmlService } from '../../miembro-html.service';

@Component({
    selector: 'app-index-miembro-liviano',
    templateUrl: './index-miembro-liviano.component.html',
    styleUrls: ['./index-miembro-liviano.component.css'],
})
export class IndexMiembroLivianoComponent implements OnInit {
    html$!: Observable<string>;

    constructor(private service: MiembroHtmlService) {}

    ngOnInit(): void {
        this.html$ = this.service.getAll();
    }
}
