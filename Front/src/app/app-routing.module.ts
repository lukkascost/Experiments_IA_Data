import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthLayoutComponent} from './shared/components/layouts/auth-layout/auth-layout.component';
import {AuthGuard} from './security/auth.guard';
import {BlankLayoutComponent} from './shared/components/layouts/blank-layout/blank-layout.component';
import {AdminLayoutSidebarLargeComponent} from './shared/components/layouts/admin-layout-sidebar-large/admin-layout-sidebar-large.component';

const adminRoutes: Routes = [
    {
        path: 'datasets',
        loadChildren: './views/datasets/datasets.module#DatasetsModule'
    },
    {
        path: 'dashboard',
        loadChildren: './views/dashboard/dashboard.module#DashboardModule'
    },
    {
        path: 'import',
        loadChildren: './views/import/import.module#ImportModule'
    }
];

const routes: Routes = [
    {
        path: '',
        canActivate: [AuthGuard],
        redirectTo: 'datasets',
        pathMatch: 'full'
    },
    {
        path: '',
        component: AuthLayoutComponent,
        children: [
            {
                path: 'sessions',
                canActivate: [AuthGuard],
                loadChildren: './views/sessions/sessions.module#SessionsModule'
            }
        ]
    },
    {
        path: '',
        component: BlankLayoutComponent,
        children: [
            {
                path: 'others',
                loadChildren: './views/others/others.module#OthersModule'
            }
        ]
    },
    {
        path: '',
        canActivate: [AuthGuard],
        component: AdminLayoutSidebarLargeComponent,
        children: adminRoutes
    },
    {
        path: '**',
        redirectTo: 'others/404'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true})],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
