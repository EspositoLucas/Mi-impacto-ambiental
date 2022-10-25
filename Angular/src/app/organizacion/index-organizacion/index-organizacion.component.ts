import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map, Observable, tap } from 'rxjs';
import { cantidadFormatTextMessage } from 'src/app/models/cantidad.model';
import { Organizacion } from 'src/app/models/organizacion.model';
import { OrganizacionService } from '../organizacion.service';

@Component({
    selector: 'app-index-organizacion',
    templateUrl: './index-organizacion.component.html',
    styleUrls: ['./index-organizacion.component.css'],
})
export class IndexOrganizacionComponent implements OnInit {
    organizaciones$!: Observable<Organizacion[]>;
    organizaciones!: Organizacion[];

    constructor(
        private organizacionService: OrganizacionService,
        private router: Router
    ) {
        this.fetchData();
    }

    ngOnInit(): void {}

    crearOrganizacion() {
        this.router.navigate(['/organizacion/crear']);
    }

    editOrganizacion(id: number) {
        this.router.navigate(['/organizacion/edit', { id }]);
    }

    deleteOrganizacion(id: number) {
        console.log(`Borrando organizacion ${id}`);
        this.fetchData();
    }

    private fetchData() {
        this.organizaciones$ = this.organizacionService
            .getAllOrganizaciones()
            .pipe(
                tap((organizaciones) =>
                    organizaciones.forEach((organizacion) =>
                        cantidadFormatTextMessage(organizacion.factorK)
                    )
                )
            );
        this.organizaciones$.subscribe(
            (organizaciones) => (this.organizaciones = organizaciones)
        );
    }
}
