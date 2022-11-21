import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { newIdTextPair } from 'src/app/models/idtextpair.model';
import { PersonaService } from 'src/app/persona/persona.service';
import { BaseSelectComponent } from '../base-select/base-select.component';

@Component({
    selector: 'app-persona-select',
    templateUrl: '../base-select/base-select.component.html',
    styleUrls: ['./persona-select.component.css'],
})
export class PersonaSelectComponent
    extends BaseSelectComponent
    implements OnInit
{
    constructor(private personaService: PersonaService) {
        super();
    }

    ngOnInit(): void {
        this.loadItems(
            this.personaService.getAllIdTextPairs().pipe(
                map((personas) => {
                    return personas.map((persona) =>
                        newIdTextPair({
                            id: persona.id,
                            text: persona.text,
                        })
                    );
                })
            )
        );
    }
}
