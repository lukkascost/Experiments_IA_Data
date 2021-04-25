import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from '../../security/auth.guard';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ExperimentsComponent} from './experiments.component';
import {ExperimentInfoComponent} from './experiment-info/experiment-info.component';

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: ExperimentsComponent,
    } , {
        path: ':id',
        component: ExperimentInfoComponent,
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
export class ExperimentsRoutingModule {
}
