import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { Organizacion } from 'src/app/models/organizacion.model';
import { OrganizacionService } from '../organizacion.service';

@Component({
    selector: 'app-edit-organizacion',
    templateUrl: '../organizacion-details.html',
    styleUrls: ['./edit-organizacion.component.css'],
})
export class EditOrganizacionComponent implements OnInit {
    organizacion!: Organizacion;

    constructor(
        private route: ActivatedRoute,
        private organizacionService: OrganizacionService
    ) {}

    ngOnInit(): void {
        this.route.paramMap
            .pipe(
                switchMap((params) =>
                    this.organizacionService.getOrganizacionById(
                        Number(params.get('id'))
                    )
                )
            )
            .subscribe((organizacion) => (this.organizacion = organizacion));
    }

    onSubmit() {
        console.log(this.organizacion);
    }
}
