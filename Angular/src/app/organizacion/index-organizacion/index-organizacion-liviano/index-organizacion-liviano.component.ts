import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { OrganizacionHtmlService } from '../../organizacion-html.service';

@Component({
    selector: 'app-index-organizacion-liviano',
    templateUrl: './index-organizacion-liviano.component.html',
    styleUrls: ['./index-organizacion-liviano.component.css'],
})
export class IndexOrganizacionLivianoComponent {
    html$!: Observable<string>;

    constructor(private service: OrganizacionHtmlService) {}

    ngOnInit(): void {
        this.html$ = this.service.getAll();
    }
}
