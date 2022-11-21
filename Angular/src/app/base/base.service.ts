import { map, Observable } from 'rxjs';
import {
    ApiHelperService,
    BaseResponse,
    ResponseWithResults,
    ResponseWithSingleResult,
} from '../api/api-helper.service';
import { Base } from '../models/base.model';
import { IdTextPair } from '../models/idtextpair.model';
import { IBaseService } from './ibase.service';

export abstract class BaseService<T extends Base> implements IBaseService<T> {
    constructor(
        protected apiHelperService: ApiHelperService,
        protected entity: string
    ) {}

    getAll(): Observable<T[]> {
        return this.apiHelperService
            .get<ResponseWithResults<T>>(this.entity)
            .pipe(map((httpResponse) => httpResponse.body!.results));
    }

    getAllIdTextPairs(): Observable<IdTextPair[]> {
        return this.apiHelperService
            .get<ResponseWithResults<IdTextPair>>(`${this.entity}/idtextpair`)
            .pipe(map((httpResponse) => httpResponse.body!.results));
    }

    getById(id: number): Observable<T> {
        return this.apiHelperService
            .get<ResponseWithSingleResult<T>>(`${this.entity}/${id}`)
            .pipe(map((httpResponse) => httpResponse.body!.result));
    }

    create(obj: T): Observable<BaseResponse | null> {
        return this.apiHelperService
            .post<BaseResponse>(this.entity, obj)
            .pipe(map((response) => response.body));
    }

    edit(obj: T): Observable<BaseResponse | null> {
        return this.apiHelperService
            .put<BaseResponse>(`${this.entity}/${obj.id}`, obj)
            .pipe(map((response) => response.body));
    }

    delete(id: number): Observable<BaseResponse | null> {
        return this.apiHelperService
            .delete<BaseResponse>(`${this.entity}/${id}`)
            .pipe(map((response) => response.body));
    }
}
