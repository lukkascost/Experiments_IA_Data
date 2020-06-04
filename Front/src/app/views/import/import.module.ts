import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {NgxPaginationModule} from 'ngx-pagination';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {SharedModule} from '../../shared/shared.module';
import {FormWizardModule} from '../../shared/components/form-wizard/form-wizard.module';
import {TextMaskModule} from 'angular2-text-mask';
import {NgxEchartsModule} from "ngx-echarts";
import {ImportRoutingModule} from "./import-routing.module";
import {ImportComponent} from "./import.component";

@NgModule({
    declarations: [ImportComponent],
    imports: [
        CommonModule,
        ImportRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        NgxEchartsModule,
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
export class ImportModule {
}
