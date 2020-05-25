import {Injectable} from '@angular/core';
import {ToastrService} from 'ngx-toastr';

@Injectable({
    providedIn: 'root'
})
export class MessageService {

    options = {timeOut: 6000, closeButton: true, progressBar: true, resetTimeoutOnDuplicate: true};

    constructor(private toastr: ToastrService) {
    }

    showSuccess(title, body) {
        this.toastr.success(body, title, this.options);
    }

    showWarning(title, body) {
        this.toastr.warning(body, title, this.options);
    }

    showError(title, body) {
        this.toastr.error(body, title, this.options);
    }

}
