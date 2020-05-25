import {Component, Input, OnInit} from '@angular/core';
import {UsersService} from '../../../views/users/users.service';
import 'rxjs-compat/add/observable/timer';
import 'rxjs-compat/add/operator/first';
import {AuthService} from '../../../security/auth.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {HandleErrorService} from '../../services/handle-error.service';
import {HttpErrorResponse} from '@angular/common/http';
import {UserDTO, UserType} from '../../../core/models/UserDTO';
import {MessageService} from '../../services/message.service';
import {NgForm} from '@angular/forms';

@Component({
    selector: 'app-user-table',
    templateUrl: './user-table.component.html',
    styleUrls: ['./user-table.component.scss']
})
export class UserTableComponent implements OnInit {

    public data: UserDTO;
    @Input() public users: UserDTO[];
    public isEditing: boolean;
    public deletedUser: UserDTO;
    UserType = UserType;
    public hardOperations = [UserType.ADMIN];
    private loading: boolean;

    constructor(
        private dataService: UsersService,
        private authService: AuthService,
        private modalService: NgbModal,
        private handleService: HandleErrorService,
        private message: MessageService) {
    }

    ngOnInit() {
        this.data = new UserDTO();
    }

    keys(Enumerator): Array<string> {
        const keys = Object.keys(Enumerator);
        return keys.slice(keys.length / 2);
    }

    async modalUser(content, row?) {
        if (this.authService.hasPermition(this.hardOperations)) {
            if (this.isEditing && row) {
                await this.dataService.getUser((<UserDTO>row).email).toPromise().then(data => {
                    this.data = (<UserDTO>data);
                    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
                }).catch(err => {
                    this.handleService.handle(err);
                });
            } else {
                this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
            }
        } else {
            this.modalService.dismissAll();
            this.handleService.handle(new HttpErrorResponse({status: 401}));
        }
    }

    async commitModalUser(form: NgForm) {
        this.loading = true;

        if (!this.isEditing) {
            await this.dataService.saveUser(this.data).toPromise()
                .then(
                    data => {
                        this.finishOperation(form);
                        this.message.showSuccess('Operação Concluída', 'Usuário cadastrado com sucesso');
                    }
                ).catch(err => {
                    this.loading = false;
                    this.handleService.handle(err);
                });
        } else {
            await this.dataService.updateUser(this.data).toPromise()
                .then(
                    data => {
                        this.finishOperation(form);
                        this.message.showSuccess('Operação Concluída', 'Usuário modificado com sucesso');
                    }
                ).catch(err => {
                    this.loading = false;
                    this.handleService.handle(err);
                });
        }
    }

    confirmUserExclusion(content, row) {
        if (this.authService.hasPermition(this.hardOperations)) {
            this.deletedUser = (<UserDTO>row);
            this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true});
        } else {
            this.modalService.dismissAll();
            this.handleService.handle(new HttpErrorResponse({status: 401}));
        }
    }

    async deleteUser() {
        this.loading = true;
        await this.dataService.deleteUser(this.deletedUser.email).toPromise()
            .then(
                data => {
                    this.finishOperation();
                    this.message.showSuccess('Operação Concluída', 'Usuário excluído com sucesso');
                }
            ).catch(err => {
                this.finishOperation();
                this.handleService.handle(err);
            });
    }

    finishOperation(form?: NgForm) {
        this.data = new UserDTO();
        this.modalService.dismissAll();
        this.loading = false;
        this.isEditing = false;
        if (form) {
            form.reset();
        }
    }

    containsPasswordValue() {
        if (this.data) {
            if (this.data.password) {
                if (this.data.password !== null || this.data.password !== undefined || this.data.password.trim().length > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    canDeleteUser(userType) {
        if (userType === UserType.ADMIN) {
            let check = false;
            let checkCount = 0;
            this.users.forEach(user => {
                if (user.userType === UserType.ADMIN) {
                    checkCount++;
                }
                if (checkCount > 1) {
                    check = true;
                }
            });
            return check;
        }
        return true;
    }

    canChangeUser(userType) {
        return this.canDeleteUser(userType) || !this.isEditing;
    }
}
