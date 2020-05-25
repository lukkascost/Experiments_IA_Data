import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserControlComponent} from './user-control/user-control.component';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from '../../security/auth.guard';

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        component: UserControlComponent
    },
    {
        path: '**',
        canActivate: [AuthGuard],
        redirectTo: '',
        pathMatch: 'full'
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
export class UsersRoutingModule {
}
