import { Router } from '@angular/router';

export abstract class BaseIndexComponent {
    constructor(protected router: Router, private entity: string) {}

    editEntidad(id: number) {
        this.router.navigate([`/${this.entity}/edit`, id]);
    }
}
