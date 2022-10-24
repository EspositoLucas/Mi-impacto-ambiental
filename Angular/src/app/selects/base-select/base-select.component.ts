import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { IdTextPair } from 'src/app/models/idtextpair.model';

@Component({
    selector: 'app-base-select',
    templateUrl: './base-select.component.html',
    styleUrls: ['./base-select.component.css'],
})
export class BaseSelectComponent implements OnInit {
    @Input() selected: IdTextPair | null = null;
    @Output() selectedChange = new EventEmitter<IdTextPair>();

    items$: Observable<IdTextPair[]>;

    constructor(items$: Observable<IdTextPair[]>) {
        this.items$ = items$;
    }

    ngOnInit(): void {}

    onNgModelChange(newSelected: IdTextPair): void {
        this.selected = newSelected;
        this.selectedChange.emit(this.selected);
    }
}
