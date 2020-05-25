import {Injectable} from '@angular/core';
import 'rxjs/add/operator/map';
import {environment} from '../../../../src/environments/environment';
import {DataHttpService} from '../../security/data-http.service';
import {UserDTO} from '../../core/models/UserDTO';

@Injectable()

export class UsersService {

    static url = environment.apiUrlBack;

    constructor(private http: DataHttpService) {
    }


    getUsers() {
        return this.http.get(UsersService.url + '/users').map(res => res.valueOf());
    }

    getUser(email: string) {
        return this.http.get(UsersService.url + '/users/' + email).map(res => res.valueOf());
    }

    saveUser(DTO: UserDTO) {
        return this.http.post(UsersService.url + '/users', DTO);
    }

    updateUser(DTO: UserDTO) {
        return this.http.patch(UsersService.url + '/users', DTO);
    }

    deleteUser(email) {
        return this.http.delete(UsersService.url + '/users?email=' + email);
    }
}
