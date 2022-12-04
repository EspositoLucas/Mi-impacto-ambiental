import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { IBaseJsonService } from 'src/app/base/ibase-json.service';

export abstract class BaseEditComponent<T> {
    constructor(
        protected route: ActivatedRoute,
        protected router: Router,
        protected entityName: string,
        protected service: IBaseJsonService<T>
    ) {}

    loadEntity() {
        this.route.paramMap
            .pipe(
                switchMap((params) =>
                    this.service.getById(Number(params.get('id')))
                )
            )
            .subscribe((entity) => (this.entity = entity));
    }

    onSubmit() {
        this.service.edit(this.entity).subscribe(() => {
            this.router.navigate([`/${this.entityName}`]);
        });
    }

    abstract get entity(): T;
    abstract set entity(obj: T);
}
