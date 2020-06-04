import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from '../../security/auth.guard';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ImportComponent} from "./import.component";

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: ImportComponent,
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
export class ImportRoutingModule {
}
