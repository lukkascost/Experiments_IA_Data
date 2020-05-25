import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {NavigationService} from '../../../../services/navigation.service';
import {SearchService} from '../../../../services/search.service';
import {AuthService} from '../../../../../security/auth.service';
import {AlarmService} from '../../../../services/alarm.service';
import {HandleErrorService} from '../../../../services/handle-error.service';
import * as moment from 'moment';
import {HubService} from '../../../../services/hub.service';

@Component({
    selector: 'app-header-sidebar-large',
    templateUrl: './header-sidebar-large.component.html',
    styleUrls: ['./header-sidebar-large.component.scss']
})
export class HeaderSidebarLargeComponent implements OnInit, OnDestroy {

    notifications: any[];
    configuredNotifications: any[] = [];
    connection: signalR.HubConnection;

    user: any;

    check = [{'check': true}];

    @Output() closeNavBarChild = new EventEmitter();

    constructor(
        private navService: NavigationService,
        public searchService: SearchService,
        public auth: AuthService,
        public alarmService: AlarmService,
        private handleService: HandleErrorService,
        private hubService: HubService
    ) {
    }

    async ngOnInit() {
        //  this.getAlarms();
        //  await this.openConnection();
    }

     async openConnection() {
         this.connection = await this.hubService.connect('alarmsHub', 'updateForAll');
         this.refreshAlarms();
         this.connection.onclose(async () => {
             await this.openConnection();
         });

    }

    getAlarms() {
        this.alarmService.getTop().toPromise()
            .then(response => {
                this.notifications = response as any[];
            })
            .catch(error => {
                this.handleService.handle(error);
            });
    }

    modifyConfigAlarms(notification, deleteAction) {
        let configuredNotification;
        if (notification.smc) {
            if (deleteAction) {
                const alarm = this.configuredNotifications
                    .find(objectAlarm => objectAlarm['identifier'] === notification.identifier);
                if (alarm) {
                    const alarmIndex = this.configuredNotifications.indexOf(alarm);
                    this.configuredNotifications.splice(alarmIndex, 1);
                }
            } else {
                configuredNotification = {
                    icon: 'i-Posterous',
                    serial: notification.smc.serial,
                    identifier: notification.identifier,
                    title: notification.alarmsTypes.description,
                    time: moment(notification.updatedAt).format('DD/MM/YYYY HH:mm:ss'),
                    status: 'primary',
                    text: `Serial do SMC: ${notification.smc.serial}`,
                    link: `/smcs/${notification.smc.smcManufacturer}/smc-info/${notification.smc.serial}/alarms`
                };
                return configuredNotification;
            }
        } else if (notification.meter) {
            let link = '';
            let text = '';
            if (notification.meter.smc) {
                text = `Serial do medidor: ${notification.meter.serial}`;
                link = `/smcs/${notification.meter.smc.smcManufacturer}` +
                    `/smc-info/${notification.meter.smc.serial}/meter-info/${notification.meter.serial}/alarms`;
            } else {
                text = `Serial do SMI: ${notification.meter.serial}`;
                link = `/meters/all/${notification.meter.serial}/alarms`;
            }
            if (deleteAction) {
                const alarm = this.configuredNotifications
                    .find(objectAlarm => objectAlarm['identifier'] === notification.identifier);
                if (alarm) {
                    const alarmIndex = this.configuredNotifications.indexOf(alarm);
                    this.configuredNotifications.splice(alarmIndex, 1);
                }
            } else {
                configuredNotification = {
                    icon: 'i-Dashboard',
                    serial: notification.meter.serial,
                    identifier: notification.identifier,
                    title: notification.alarmsTypes.description,
                    time: moment(notification.updatedAt).format('DD/MM/YYYY HH:mm:ss'),
                    status: 'primary',
                    text: text,
                    link: link
                };
                return configuredNotification;
            }
        }
    }

    refreshAlarms() {
        this.connection.on('updateForAll', (data, deleteAction) => {
            if (data) {
                if (deleteAction) {
                    this.modifyConfigAlarms(data , true);
                    this.configuredNotifications = this.configuredNotifications.slice();
                } else {
                    const alarm = this.modifyConfigAlarms(data , false);
                    const wantedAlarm = this.configuredNotifications
                        .find(configuredNotification => configuredNotification['identifier'] === alarm['identifier']);
                    if (wantedAlarm) {
                        const wantedAlarmIndex = this.configuredNotifications.indexOf(wantedAlarm);
                        if (wantedAlarmIndex > -1) {
                            this.configuredNotifications[wantedAlarmIndex] = alarm;
                        }
                    } else {
                        this.configuredNotifications.unshift(alarm);
                    }
                }
            }
        });
    }

    toggelSidebar() {
        const state = this.navService.sidebarState;
        if (state.childnavOpen && state.sidenavOpen) {
            return state.childnavOpen = false;
        }
        if (!state.childnavOpen && state.sidenavOpen) {
            return state.sidenavOpen = false;
        }
        if (!state.sidenavOpen && !state.childnavOpen) {
            state.sidenavOpen = true;
            setTimeout(() => {
                state.childnavOpen = true;
            }, 50);
        }
    }

    signout() {
        this.auth.signout();
    }

    closeNavChild() {
        this.closeNavBarChild.emit(this.check.slice().concat(this.check.slice()));
    }

    ngOnDestroy(): void {
        this.hubService.disconnect(this.connection);
        this.connection = null;
    }

}
