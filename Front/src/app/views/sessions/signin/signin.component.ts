import {Component, OnInit} from '@angular/core';
import {SharedAnimations} from 'src/app/shared/animations/shared-animations';
import {AuthService} from '../../../security/auth.service';
import {ToastrService} from 'ngx-toastr';
import {ResolveEnd, ResolveStart, RouteConfigLoadEnd, RouteConfigLoadStart, Router} from '@angular/router';
import {UserDTO} from '../../../core/models/UserDTO';
import {NgForm} from '@angular/forms';
import {HandleErrorService} from '../../../shared/services/handle-error.service';

@Component({
    selector: 'app-signin',
    templateUrl: './signin.component.html',
    styleUrls: ['./signin.component.scss'],
    animations: [SharedAnimations]
})
export class SigninComponent implements OnInit {

    loading: boolean;
    loadingText: string;

    user = new UserDTO();

    constructor(
        private auth: AuthService,
        private router: Router,
        private toastr: ToastrService,
        private handleService: HandleErrorService
    ) {
    }

    async ngOnInit() {
        await this.router.events.toPromise().then(event => {
            if (event instanceof RouteConfigLoadStart || event instanceof ResolveStart) {
                this.loadingText = 'Redirecionando-se para os Medidores...';

                this.loading = true;
            }
            if (event instanceof RouteConfigLoadEnd || event instanceof ResolveEnd) {
                this.loading = false;
            }
        });
    }

    async signin(form: NgForm) {
        this.loading = true;
        this.loadingText = 'Entrando...';

        await this.auth.signin(form.value)
            .then(res => {
                if (localStorage.getItem('returnUrl')) {
                    this.router.navigate([localStorage.getItem('returnUrl')]);
                } else {
                    this.router.navigate(['meters/all']);
                }
                this.loading = false;
            }).catch(error => {
                this.loading = false;
                this.handleService.handle(error);
            });
    }

}
