import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from '../../security/auth.guard';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DashboardComponent} from "./dashboard.component";

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: DashboardComponent,
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
export class DashboardRoutingModule {
}
