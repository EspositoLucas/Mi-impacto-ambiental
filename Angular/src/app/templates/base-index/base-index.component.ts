import { Router } from '@angular/router';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { IBaseService } from 'src/app/base/ibase.service';
import { Base } from 'src/app/models/base.model';

export abstract class BaseIndexComponent<T extends Base> {
    resultsSubject$ = new Subject<void>();
    results$!: Observable<T[]>;

    constructor(
        protected router: Router,
        private entity: string,
        private service: IBaseService<T>
    ) {}

    onInit(): void {
        this.results$ = this.resultsSubject$.asObservable().pipe(
            startWith(null),
            switchMap(() => this.service.getAll())
        );
    }

    editEntidad(id: number) {
        this.router.navigate([`/${this.entity}/edit`, id]);
    }

    deleteEntidad(entity: T, message: string) {
        const eliminar = confirm(
            `Esta seguro/a que desea eliminar a ${message}?`
        );
        if (eliminar) {
            console.log(`Borrando ${message} (ID ${entity.id})`);
            this.service
                .delete(entity.id!)
                .subscribe(() => this.resultsSubject$.next());
        }
    }
}
