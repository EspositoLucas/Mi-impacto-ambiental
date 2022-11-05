import { Observable } from 'rxjs';

export interface IBaseService<T> {
    getAll(): Observable<T[]>;
    getById(id: number): Observable<T>;
    create(obj: T): Observable<void>;
    edit(obj: T): Observable<void>;
    delete(id: number): Observable<void>;
}
