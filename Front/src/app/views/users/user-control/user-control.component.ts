import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from '../../../security/auth.service';
import {UserDTO, UserType} from '../../../core/models/UserDTO';
import {UsersService} from '../users.service';
import {HandleErrorService} from '../../../shared/services/handle-error.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {NgForm} from '@angular/forms';
import {MessageService} from '../../../shared/services/message.service';
import {HubService} from '../../../shared/services/hub.service';
import * as signalR from '@microsoft/signalr';

@Component({
    selector: 'app-user-control',
    templateUrl: './user-control.component.html',
    styleUrls: ['./user-control.component.scss']
})
export class UserControlComponent implements OnInit, OnDestroy {
    users: UserDTO[];
    updatedList: UserDTO[];
    data: UserDTO;
    currentUser: UserDTO;
    user: UserDTO;

    connection: signalR.HubConnection;

    public deleteAction = false;

    public UserType = UserType;

    public hardOperations = [UserType.ADMIN];
    public isLoading: boolean;
    private loading: boolean;

    constructor(
        private dataService: UsersService,
        public authService: AuthService,
        private handleService: HandleErrorService,
        private modalService: NgbModal,
        private message: MessageService,
        private hubService: HubService) {
    }

    async ngOnInit() {
        this.currentUser = this.authService.getuser();
        this.dataService.getUsers().toPromise()
            .then(data => {
                this.updatedList = (<UserDTO[]>data);
                this.users = this.updatedList.slice();
            }).catch(err => this.handleService.handle(err));
        await this.openConnection();
    }

    async openConnection() {
        this.connection = await this.hubService.connect('userHub', 'updateForAll');
        this.refreshTable();
        this.connection.onclose(async () => {
            await this.openConnection();
        });
    }

    refreshTable() {
        this.connection.on('updateForAll', (data, deleteAction) => {
            if (data) {
                this.user = (<UserDTO>data);
                this.deleteAction = deleteAction;
                this.updateUsersList();
            }
            this.isLoading = false;
        });
    }

    updateUsersList() {
        const user = this.users.find(userList => userList.email === this.user.email);
        if (user) {
            const userIndex = this.users.indexOf(user);
            if (this.deleteAction) {
                this.users.splice(userIndex, 1);
            } else {
                this.users[userIndex] = this.user;
            }
            this.updatedList = this.users.slice();
        } else {
            this.users.push((<UserDTO>this.user));
            this.updatedList = this.users.slice();
        }
    }

    keys(Enumerator): Array<string> {
        const keys = Object.keys(Enumerator);
        return keys.slice(keys.length / 2);
    }

    modalUser(content, email?) {
        this.dataService.getUser(email).toPromise()
            .then(
                data => {
                    this.data = (<UserDTO>data);
                    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
                }
            ).catch(err => this.handleService.handle(err));
    }

    commitModalUser(form?: NgForm) {
        this.loading = true;

        this.dataService.updateUser(this.data).toPromise()
            .then(
                data => {
                    this.finishOperation(form);
                    this.message.showSuccess('Operação Concluída', 'Usuário modificado com sucesso');
                    this.authService.signout();
                }
            ).catch(err => {
            this.finishOperation(form);
            this.handleService.handle(err);
        });
    }

    finishOperation(form?: NgForm) {
        this.modalService.dismissAll();
        this.loading = false;
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

    canModifyUser(userType) {
        let check = false;
        if (userType === UserType.ADMIN) {
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
        return false;
    }

    ngOnDestroy() {
        this.hubService.disconnect(this.connection);
        this.connection = null;
    }

}
