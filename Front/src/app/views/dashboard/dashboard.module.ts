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
import {DashboardRoutingModule} from "./dashboard-routing.module";
import {DashboardComponent} from "./dashboard.component";

@NgModule({
    declarations: [DashboardComponent],
    imports: [
        CommonModule,
        DashboardRoutingModule,
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
export class DashboardModule {
}
