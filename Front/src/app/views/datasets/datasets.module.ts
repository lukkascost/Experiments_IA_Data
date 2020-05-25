import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {NgxPaginationModule} from 'ngx-pagination';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {SharedModule} from '../../shared/shared.module';
import {FormWizardModule} from '../../shared/components/form-wizard/form-wizard.module';
import {TextMaskModule} from 'angular2-text-mask';
import {DatasetsComponent} from './datasets.component';
import {DatasetsRoutingModule} from './datasets-routing.module';
import { DatasetInfoComponent } from './dataset-info/dataset-info.component';

@NgModule({
    declarations: [DatasetsComponent, DatasetInfoComponent],
    imports: [
        CommonModule,
        DatasetsRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        NgxDatatableModule,
        NgxPaginationModule,
        NgbModule,
        SharedModule,
        FormWizardModule,
        TextMaskModule
    ],
    exports: [],
    providers: [
    ]
})
export class DatasetsModule {
}
