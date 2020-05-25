import {environment} from '../../../environments/environment';
import {Injectable} from '@angular/core';
import {DataHttpService} from '../../security/data-http.service';


@Injectable({
    providedIn: 'root'
})
export class DatasetService {
    constructor(private http: DataHttpService) {
    }
    static url = environment.apiUrlBack + '/datasets';

    getDatasets() {
        return this.http.get(`${DatasetService.url}`).map(res => res.valueOf());
    }
}
