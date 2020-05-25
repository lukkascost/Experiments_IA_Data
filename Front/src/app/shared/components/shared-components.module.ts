import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BtnLoadingComponent} from './btn-loading/btn-loading.component';
import {FeatherIconComponent} from './feather-icon/feather-icon.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from '@angular/router';
import {SharedPipesModule} from '../pipes/shared-pipes.module';
import {SearchModule} from './search/search.module';
import {SharedDirectivesModule} from '../directives/shared-directives.module';
import {PerfectScrollbarModule} from 'ngx-perfect-scrollbar';
import {LayoutsModule} from './layouts/layouts.module';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {UserTableComponent} from './user-table/user-table.component';
import {TableLoadingComponent} from './table-loading/table-loading.component';
import {UiSwitchModule} from 'ngx-toggle-switch';
import {ButtonsRelayOperationComponent} from './buttons-relay-operation/buttons-relay-operation.component';
import {DatetimePickerComponent} from './datetime-picker/datetime-picker.component';
import {CardStatusComponent} from './card-status/card-status.component';
import {CardInfoComponent} from './card-info/card-info.component';
import {DatasetTableComponent} from './dataset-table/dataset-table.component';

const components = [
    BtnLoadingComponent,
    FeatherIconComponent,
    UserTableComponent,
    TableLoadingComponent,
    ButtonsRelayOperationComponent,
    DatetimePickerComponent,
    CardStatusComponent,
    CardInfoComponent,
    DatasetTableComponent
];

@NgModule({
    imports: [
        CommonModule,
        RouterModule,
        LayoutsModule,
        SharedPipesModule,
        SharedDirectivesModule,
        SearchModule,
        PerfectScrollbarModule,
        NgbModule,
        NgxDatatableModule,
        FormsModule,
        ReactiveFormsModule,
        UiSwitchModule,
    ],
    declarations: components,
    exports: components
})
export class SharedComponentsModule {
}
