import {environment} from '../../../environments/environment';
import {Injectable} from '@angular/core';
import {DataHttpService} from '../../security/data-http.service';
import {DatasetRegisterDTO} from '../../core/models/DatasetDTO';
import {SampleRegisterDTO} from '../../core/models/SampleDTO';


@Injectable({
    providedIn: 'root'
})
export class SampleService {
    constructor(private http: DataHttpService) {
    }
    static url = environment.apiUrlBack + '/samples';

    deleteSample(id: string) {
        return this.http.delete(`${SampleService.url}` + '/' + id).map(res => res.valueOf());
    }
}
