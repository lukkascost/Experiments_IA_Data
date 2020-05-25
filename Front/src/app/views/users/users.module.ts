import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {UsersRoutingModule} from './users-routing.module';
import {UserControlComponent} from './user-control/user-control.component';
import {UsersService} from './users.service';
import {SharedComponentsModule} from '../../shared/components/shared-components.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
    declarations: [UserControlComponent],
    imports: [
        CommonModule,
        NgbModule,
        UsersRoutingModule,
        SharedComponentsModule,
        ReactiveFormsModule,
        FormsModule
    ],
    providers: [
        UsersService
    ]
})
export class UsersModule {
}
