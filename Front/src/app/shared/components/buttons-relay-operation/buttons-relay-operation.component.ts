import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserType} from '../../../core/models/UserDTO';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {HttpErrorResponse} from '@angular/common/http';
import {AuthService} from '../../../security/auth.service';
import {HandleErrorService} from '../../services/handle-error.service';

@Component({
    selector: 'app-buttons-relay-operation',
    templateUrl: './buttons-relay-operation.component.html',
    styleUrls: ['./buttons-relay-operation.component.scss']
})
export class ButtonsRelayOperationComponent implements OnInit {

    @Input() checkedMeters: string[];
    @Input() meterSerial: string;
    @Input() commandQueue: number;

    @Output() relayOn = new EventEmitter();
    @Output() relayOff = new EventEmitter();
    @Output() getRelayStatus = new EventEmitter();

    @Input() requestingRelayStatus: boolean;
    @Input() relayDeactivating: boolean;
    @Input() relayActivating: boolean;

    public hardOperations = [UserType.ADMIN, UserType.OPERATOR];

    constructor(
        private modalService: NgbModal,
        public authService: AuthService,
        private handleService: HandleErrorService) {
    }

    ngOnInit() {
    }

    confirmRelayOperation(content) {
        if (this.authService) {
            this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', centered: true});
        } else {
            this.handleService.handle(new HttpErrorResponse({status: 401}));
        }
    }

    allRelayOn() {
        this.finishOperation();
        if (this.checkedMeters) {
            this.relayOn.emit(this.checkedMeters.slice());
        } else {
            this.relayOn.emit();
        }
    }

    allRelayOff() {
        this.finishOperation();
        if (this.checkedMeters) {
            this.relayOff.emit(this.checkedMeters.slice());
        } else {
            this.relayOff.emit();
        }
    }

    allGetRelay() {
        if (this.checkedMeters) {
            this.getRelayStatus.emit(this.checkedMeters.slice());
        } else {
            this.getRelayStatus.emit();
        }
    }

    finishOperation() {
        this.modalService.dismissAll();
    }

}
