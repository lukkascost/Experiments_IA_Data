import {Injectable} from '@angular/core';
import {DataHttpService} from '../../security/data-http.service';
import {environment} from '../../../environments/environment';
import {HttpHeaders} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class AlarmService {

    static url = environment.apiUrlBack + '/alarms';

    constructor(private http: DataHttpService) {
    }

    getAlarmsByMeter(meterSerial: string) {
        return this.http.get(`${AlarmService.url}/alarmsbymeter/${meterSerial}`).map(res => res.valueOf());
    }

    getAlarmsBySmc(smcSerial: string) {
        return this.http.get(`${AlarmService.url}/alarmsbysmc/${smcSerial}`).map(res => res.valueOf());
    }

    getAlarmsMetersBySmc(smcSerial: string) {
        return this.http.get(`${AlarmService.url}/alarmsmetersbysmc/${smcSerial}`).map(res => res.valueOf());
    }

    getTop() {
        return this.http.get(`${AlarmService.url}/top`).map(res => res.valueOf());
    }

    changeReadProperty(identifier: string) {
        return this.http.patch(`${AlarmService.url}/changeReadAlarm`, '"' + identifier + '"',
            {headers: new HttpHeaders({'Content-Type': 'application/json'})}).map(res => res.valueOf());
    }
}
