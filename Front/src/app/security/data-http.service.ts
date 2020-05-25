import {Injectable} from '@angular/core';
import {HttpClient, HttpHandler} from '@angular/common/http';
import {AuthService} from './auth.service';
import {Observable} from 'rxjs';

export class NotAuthenticatedError {
}

@Injectable({
    providedIn: 'root'
})
export class DataHttpService extends HttpClient {

    constructor(
        private auth: AuthService,
        private httpHandler: HttpHandler
    ) {
        super(httpHandler);
    }

    public delete<T>(url: string, options?: any): Observable<T> {
        return this.makeRequest<T>(() => super.delete<T>(url, options));
    }

    public patch<T>(url: string, body: any, options?: any): Observable<T> {
        return this.makeRequest<T>(() => super.patch<T>(url, body, options));
    }

    public head<T>(url: string, options?: any): Observable<T> {
        return this.makeRequest<T>(() => super.head<T>(url, options));
    }

    public options<T>(url: string, options?: any): Observable<T> {
        return this.makeRequest<T>(() => super.options<T>(url, options));
    }

    public get<T>(url: string, options?: any): Observable<T> {
        return this.makeRequest<T>(() => super.get<T>(url, options));
    }

    public post<T>(url: string, body: any, options?: any): Observable<T> {
        return this.makeRequest<T>(() => super.post<T>(url, body, options));
    }

    public put<T>(url: string, body: any, options?: any): Observable<T> {
        return this.makeRequest<T>(() => super.put<T>(url, body, options));
    }

    private makeRequest<T>(fn: Function): Observable<T> {
        // if (this.auth.isInvalidAccessToken()) {
        //     throw new NotAuthenticatedError();
        // } else {
            return fn();
        // }
    }
}
