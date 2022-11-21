import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Persona } from 'src/app/models/persona.model';
import { BaseIndexComponent } from 'src/app/templates/base-index/base-index.component';
import { PersonaService } from '../persona.service';

@Component({
    selector: 'app-index-persona',
    templateUrl: './index-persona.component.html',
    styleUrls: ['./index-persona.component.css'],
})
export class IndexPersonaComponent
    extends BaseIndexComponent<Persona>
    implements OnInit
{
    constructor(router: Router, service: PersonaService) {
        super(router, 'persona', service);
    }

    ngOnInit(): void {
        super.onInit();
    }
}
