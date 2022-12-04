import { map, Observable } from 'rxjs';
import { ApiHtmlHelperService } from '../api/api-html-helper.service';
import { IBaseHtmlService } from './ibase-html.service';

export abstract class BaseHtmlService implements IBaseHtmlService {
    constructor(
        protected apiHelperService: ApiHtmlHelperService,
        protected entity: string
    ) {}

    getAll(): Observable<string> {
        return this.apiHelperService
            .get(this.entity)
            .pipe(map((httpResponse) => httpResponse.body!));
    }
}
