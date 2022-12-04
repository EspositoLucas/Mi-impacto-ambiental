import { Injectable } from '@angular/core';
import { ApiHtmlHelperService } from '../api/api-html-helper.service';
import { BaseHtmlService } from '../base/base-html.service';

@Injectable({
    providedIn: 'root',
})
export class MiembroHtmlService extends BaseHtmlService {
    constructor(apiHelperService: ApiHtmlHelperService) {
        super(apiHelperService, 'miembro');
    }
}
