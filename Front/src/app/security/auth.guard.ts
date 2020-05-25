import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from './auth.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(
        private router: Router,
        private auth: AuthService
    ) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        // if (route['_routerState']['url'] === '/sessions/signin') {
        //     if (this.auth.isInvalidAccessToken()) {
        //         return true;
        //     }
        //     this.router.navigate(['/meters/all']);
        //     return false;
        // } else if (this.auth.isInvalidAccessToken()) {
        //     this.router.navigate(['/sessions/signin']);
        //     return false;
        // }
        return true;
    }
}
