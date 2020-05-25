import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AuthGuard} from './auth.guard';
import {JwtHelperService, JwtInterceptor, JwtModule} from '@auth0/angular-jwt';
import {environment} from '../../environments/environment';
import {HTTP_INTERCEPTORS} from '@angular/common/http';

export function tokenGetter() {
    return localStorage.getItem('token');
}

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        JwtModule.forRoot({
            config: {
                tokenGetter: tokenGetter,
                whitelistedDomains: [environment.tokenWhitelistedDomains],
                blacklistedRoutes: [environment.tokenBlacklistedDomains]
            }
        }),
    ],
    providers: [
        AuthGuard,
        JwtHelperService,
        {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true}
    ]
})
export class SecurityModule {
}
