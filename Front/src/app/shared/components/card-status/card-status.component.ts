import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: 'app-card-status',
    templateUrl: './card-status.component.html',
    styleUrls: ['./card-status.component.scss']
})
export class CardStatusComponent implements OnInit {

    @Input() isLoading: boolean;
    @Input() icon: string;
    @Input() spinner: boolean;
    @Input() spinnerClass: string;

    constructor() {
    }

    ngOnInit() {
    }

}
