import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { newMiembro, Miembro } from 'src/app/models/miembro.model';
import { MiembroJsonService } from '../miembro-json.service';

@Component({
    selector: 'app-crear-miembro',
    templateUrl: '../miembro-details.html',
    styleUrls: ['./crear-miembro.component.css'],
})
export class CrearMiembroComponent implements OnInit {
    miembro: Miembro;

    constructor(
        private miembroService: MiembroJsonService,
        private router: Router
    ) {
        this.miembro = newMiembro();
    }

    ngOnInit(): void {}

    onSubmit() {
        this.miembroService.create(this.miembro).subscribe((response) => {
            alert(
                `GUARDAR ESTE NUMERO: enviarselo a la persona para que registre su usuario.\n\n${response?.idSolicitud}`
            );
            this.router.navigate(['/miembro']);
        });
    }
}
