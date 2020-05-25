import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {JwtHelperService} from '@auth0/angular-jwt';
import {environment} from '../../environments/environment';
import {UserDTO} from '../core/models/UserDTO';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    jwtPayload: any;

    constructor(
        private http: HttpClient,
        private router: Router,
        private toastr: ToastrService,
        private jwtHelperService: JwtHelperService,
        private modalService: NgbModal) {
        this.loadToken();
    }

    getuser() {
        if (localStorage.getItem('currentUser') && JSON.parse(localStorage.getItem('currentUser'))['user']) {
            return (<UserDTO>JSON.parse(localStorage.getItem('currentUser'))['user']);
        }
    }

    hasPermition(roles: number[]) {
        if (this.getuser()) {
            return roles.some(role => role === this.getuser().userType);
        }
    }

    async signin(credentials) {
        const user = new UserDTO();
        user.email = credentials.email;
        user.password = credentials.password;
        let msg: string;

        return await this.http.post(`${environment.apiUrlBack}/users/login`, user)
            .toPromise()
            .then(response => {
                if (response && response['authenticated']) {
                    localStorage.setItem('currentUser', JSON.stringify(response));
                    this.storeToken(response['accessToken']);
                } else {
                    msg = response['message'];
                    this.toastr.error(msg, 'Erro de autenticação', {timeOut: 10000, closeButton: true, progressBar: true});
                }
            });
    }

    storeToken(token: string) {
        this.jwtPayload = this.jwtHelperService.decodeToken(token);
        localStorage.setItem('token', token);
    }

    loadToken() {
        const token = localStorage.getItem('token');
        if (token) {
            this.storeToken(token);
        }
    }

    clearSession() {
        localStorage.removeItem('token');
        localStorage.removeItem('currentUser');
        localStorage.removeItem('returnUrl');
        this.jwtPayload = null;
        this.modalService.dismissAll();
    }

    isInvalidAccessToken() {
        const token = localStorage.getItem('token');
        return !token || this.jwtHelperService.isTokenExpired(token);
    }

    signout(returnUrl?) {
        this.clearSession();
        if (returnUrl) {
            localStorage.setItem('returnUrl', returnUrl);
        }
        this.router.navigate(['/sessions/signin']);
    }
}
