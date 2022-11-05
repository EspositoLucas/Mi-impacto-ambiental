import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-index-template',
    templateUrl: './index-template.component.html',
    styleUrls: ['./index-template.component.css'],
})
export class IndexTemplateComponent implements OnInit {
    @Input() entity!: string;

    constructor(private router: Router) {}

    ngOnInit(): void {}

    crearEntidad() {
        this.router.navigate([`/${this.entity}/crear`]);
    }
}
