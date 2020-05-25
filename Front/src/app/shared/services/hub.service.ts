import {Injectable} from '@angular/core';
import {HandleErrorService} from './handle-error.service';
import * as signalR from '@microsoft/signalr';
import {environment} from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class HubService {

    constructor(private handleService: HandleErrorService) {
    }

    public async connect(url: string, methodName: string) {
        const connection = this.makeConnection(url);
        await connection.start().catch(error => {
            this.handleService.handle(error);
            setTimeout(async () => await this.connect(url, methodName), 5000);
        });
        // await connection.invoke(methodName, null, false);
        return connection;
    }

    public disconnect(connection: signalR.HubConnection) {
        if (connection) {
            connection.stop().catch(error => {
                this.handleService.handle(error);
            });
        }
    }

    private makeConnection(url: string) {
        const connection = new signalR.HubConnectionBuilder()
            .withUrl(`${environment.apiUrlBack}/${url}`, {
                skipNegotiation: true,
                transport: signalR.HttpTransportType.WebSockets
            })
            .withAutomaticReconnect()
            .configureLogging(signalR.LogLevel.Trace)
            .build();
        connection.serverTimeoutInMilliseconds = environment.websocketExpiration;
        return connection;
    }
}
