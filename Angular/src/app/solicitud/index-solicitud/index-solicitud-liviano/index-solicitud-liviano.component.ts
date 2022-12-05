import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
    selector: 'app-index-solicitud-liviano',
    templateUrl: './index-solicitud-liviano.component.html',
    styleUrls: ['./index-solicitud-liviano.component.css'],
})
export class IndexSolicitudLivianoComponent implements OnInit {
    @Input() html!: string;

    safeHtml!: SafeHtml;

    constructor(private sanitizer: DomSanitizer) {}

    ngOnInit(): void {
        if (this.html) {
            this.safeHtml = this.sanitizer.bypassSecurityTrustHtml(this.html);
        }
    }
}
