import { Observable } from 'rxjs';
import { BaseResponse } from '../api/api-json-helper.service';

export interface IBaseJsonService<T> {
    getAll(): Observable<T[]>;
    getById(id: number): Observable<T>;
    create(obj: T): Observable<BaseResponse | null>;
    edit(obj: T): Observable<BaseResponse | null>;
    delete(id: number): Observable<BaseResponse | null>;
}
