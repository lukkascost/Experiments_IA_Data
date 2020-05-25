import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: 'btn-loading',
    templateUrl: './btn-loading.component.html',
    styleUrls: ['./btn-loading.component.scss']
})
export class BtnLoadingComponent implements OnInit {
    @Input('loading') loading: boolean;
    @Input('btnClass') btnClass: string;
    @Input('loadingText') loadingText = 'Please wait';
    @Input('type') type: 'button' | 'submit' = 'submit';
    @Input('disabled') disabled: boolean;

    constructor() {
    }

    ngOnInit() {
    }

}
