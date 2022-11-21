import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { newPersona, Persona } from 'src/app/models/persona.model';
import { PersonaService } from '../persona.service';
@Component({
    selector: 'app-crear-persona',
    templateUrl: '../persona-details.html',
    styleUrls: ['./crear-persona.component.css'],
})
export class CrearPersonaComponent implements OnInit {
    persona: Persona;

    constructor(
        private personaService: PersonaService,
        private router: Router
    ) {
        this.persona = newPersona();
    }

    ngOnInit(): void {}

    onSubmit() {
        this.personaService.create(this.persona).subscribe(() => {
            this.router.navigate(['/persona']);
        });
    }
}
