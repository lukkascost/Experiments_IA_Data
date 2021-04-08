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
export class RoundsService {
    constructor(private http: DataHttpService) {
    }
    static url = environment.apiUrlBack + '/rounds';

    get(page, number: number) {
        return this.http.get(`${RoundsService.url}` + '?page=' + page + '&size=' + number).map(res => res.valueOf());
    }

    create(selectedDataset: ExperimentRegisterDTO) {
        return this.http.post(`${RoundsService.url}`, selectedDataset).map(res => res.valueOf());

    }

    delete(id: string) {
        return this.http.delete(`${RoundsService.url}` + '?id=' + id).map(res => res.valueOf());
    }

    getByExperimentId(id: string) {
        return this.http.get(`${RoundsService.url}` + '?experiment_id=' + id).map(res => res.valueOf());
    }
}
