import {Injectable} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {AuthService} from '../../security/auth.service';
import {MessageService} from './message.service';
import {Router} from '@angular/router';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Injectable({
    providedIn: 'root'
})
export class HandleErrorService {

    constructor(
        private message: MessageService,
        private auth: AuthService,
        private router: Router,
        private modalService: NgbModal
    ) {
    }

    handle(errorResponse: any) {
        let msg: string;

        if (typeof errorResponse === 'string') {
            msg = errorResponse;

        } else if (errorResponse instanceof HttpErrorResponse
            && errorResponse.status >= 400 && errorResponse.status <= 499) {
            msg = errorResponse.error;

            if (errorResponse.status === 401 || errorResponse.status === 403) {
                msg = 'Você não tem permissão para executar esta ação';
                this.modalService.dismissAll();
                this.auth.signout(this.router.url);
            }

            if (errorResponse.status === 404) {
                this.modalService.dismissAll();
                this.router.navigate(['/others/404']);
            }

            console.error('Ocorreu um erro', errorResponse);

        } else {
            msg = 'Erro ao processar serviço remoto. Tente novamente.';
            console.error('Ocorreu um erro', errorResponse);
        }

        this.message.showError('Operação Cancelada', msg);
    }
}
