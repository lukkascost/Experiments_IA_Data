import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from '../../security/auth.guard';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DatasetsComponent} from './datasets.component';
import {DatasetInfoComponent} from './dataset-info/dataset-info.component';

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: DatasetsComponent,
    },
    {
        path: ':id',
        component: DatasetInfoComponent,
    }
];

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class DatasetsRoutingModule {
}
