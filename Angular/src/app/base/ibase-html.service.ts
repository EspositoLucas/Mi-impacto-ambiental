import { Observable } from 'rxjs';

export interface IBaseHtmlService {
    getAll(): Observable<string>;
}
