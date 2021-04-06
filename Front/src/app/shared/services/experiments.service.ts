import {environment} from '../../../environments/environment';
import {Injectable} from '@angular/core';
import {DataHttpService} from '../../security/data-http.service';
import {DatasetRegisterDTO} from '../../core/models/DatasetDTO';
import {SampleRegisterDTO} from '../../core/models/SampleDTO';
import {Observable} from 'rxjs';
import {ExperimentRegisterDTO} from '../../core/models/ExperimentsDTO';


@Injectable({
    providedIn: 'root'
})
export class ExperimentsService {
    constructor(private http: DataHttpService) {
    }
    static url = environment.apiUrlBack + '/experiments';

    get(page, number: number) {
        return this.http.get(`${ExperimentsService.url}` + '?page=' + page + '&size=' + number).map(res => res.valueOf());
    }

    create(selectedDataset: ExperimentRegisterDTO) {
        return this.http.post(`${ExperimentsService.url}`, selectedDataset).map(res => res.valueOf());

    }

    delete(id: string) {
        return this.http.delete(`${ExperimentsService.url}` + '?id=' + id).map(res => res.valueOf());
    }

    update(selectedDataset: ExperimentRegisterDTO) {
        return this.http.put(`${ExperimentsService.url}`, selectedDataset).map(res => res.valueOf());

    }

    getById(id: string) {
        return this.http.get(`${ExperimentsService.url}` + '?id=' + id).map(res => res.valueOf());

    }
}
