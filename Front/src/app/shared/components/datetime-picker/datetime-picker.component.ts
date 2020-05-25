import {AfterViewInit, Component, forwardRef, Injector, Input, OnInit, ViewChild} from '@angular/core';
import {DateTimeModel} from './DateTimeModel';
import {NgbDatepicker, NgbDateStruct, NgbPopover, NgbPopoverConfig, NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';
import {noop} from 'rxjs';
import {ControlValueAccessor, NG_VALUE_ACCESSOR, NgControl} from '@angular/forms';
import {DatePipe} from '@angular/common';

@Component({
    selector: 'app-datetime-picker',
    templateUrl: './datetime-picker.component.html',
    styleUrls: ['./datetime-picker.component.scss'],
    providers: [
        DatePipe,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => DatetimePickerComponent),
            multi: true
        }
    ]
})
export class DatetimePickerComponent implements ControlValueAccessor, OnInit, AfterViewInit {
    @Input()
    dateString: string;

    @Input()
    inputDatetimeFormat = 'dd/MM/yyyy HH:mm:ss';
    @Input()
    hourStep = 1;
    @Input()
    minuteStep = 15;
    @Input()
    secondStep = 30;
    @Input()
    seconds = true;

    @Input()
    disabled = false;
    public ngControl: NgControl;
    private showTimePickerToggle = false;
    private datetime: DateTimeModel = new DateTimeModel();
    @ViewChild(NgbDatepicker, {static: false})
    private dp: NgbDatepicker;
    @ViewChild(NgbPopover, {static: false})
    private popover: NgbPopover;
    private onTouched: () => void = noop;
    private onChange: (_: any) => void = noop;

    constructor(private config: NgbPopoverConfig, private inj: Injector) {
        config.autoClose = 'outside';
        config.placement = 'bottom';
    }

    ngOnInit(): void {
        this.ngControl = this.inj.get(NgControl);
    }

    ngAfterViewInit(): void {
        this.popover.hidden.subscribe($event => {
            this.showTimePickerToggle = false;
        });
    }

    writeValue(newModel: string) {
        if (newModel) {
            this.datetime = Object.assign(this.datetime, DateTimeModel.fromLocalString(newModel));
            this.dateString = newModel;
            this.setDateStringModel();
        } else {
            this.datetime = new DateTimeModel();
        }
    }

    registerOnChange(fn: any): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }

    toggleDateTimeState($event) {
        this.showTimePickerToggle = !this.showTimePickerToggle;
        $event.stopPropagation();
    }

    setDisabledState?(isDisabled: boolean): void {
        this.disabled = isDisabled;
    }

    onDateChange($event: string | NgbDateStruct) {
        if ($event['year']) {
            $event = `${$event['year']}-${$event['month']}-${$event['day']}`;
        }

        const date = DateTimeModel.fromLocalString($event.toString());

        if (!date) {
            this.dateString = this.dateString;
            return;
        }

        if (!this.datetime) {
            this.datetime = date;
        }

        this.datetime.year = date.year;
        this.datetime.month = date.month;
        this.datetime.day = date.day;

        this.setDateStringModel();
    }

    onTimeChange(event: NgbTimeStruct) {
        this.datetime.hour = event.hour;
        this.datetime.minute = event.minute;
        this.datetime.second = event.second;

        this.setDateStringModel();
    }

    setDateStringModel() {
        this.dateString = this.datetime.toString();

        this.onChange(this.dateString);
    }

    inputBlur($event) {
        this.onTouched();
    }
}
