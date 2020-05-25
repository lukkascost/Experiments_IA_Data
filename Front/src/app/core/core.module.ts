import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SharedModule} from '../shared/shared.module';
import {HttpClientModule} from '@angular/common/http';
import {HandleErrorService} from '../shared/services/handle-error.service';
import {AuthService} from '../security/auth.service';
import {DataHttpService} from '../security/data-http.service';
import {MessageService} from '../shared/services/message.service';
import {NgbDateParserFormatter} from '@ng-bootstrap/ng-bootstrap';
import {NgbDatePTParserFormatter} from './util/NgbDatePTParserFormatter';
import {HubService} from '../shared/services/hub.service';

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        SharedModule,
        HttpClientModule
    ],
    exports: [
        SharedModule,
        HttpClientModule
    ],
    providers: [
        HandleErrorService,
        MessageService,
        AuthService,
        DataHttpService,
        HubService,
        {provide: NgbDateParserFormatter, useClass: NgbDatePTParserFormatter}
    ],
})
export class CoreModule {
}
