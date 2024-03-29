import {environment} from '../../../environments/environment';
import {Injectable} from '@angular/core';
import {DataHttpService} from '../../security/data-http.service';
import {DatasetRegisterDTO} from '../../core/models/DatasetDTO';
import {SampleRegisterDTO} from '../../core/models/SampleDTO';
import {Observable} from 'rxjs';


@Injectable({
    providedIn: 'root'
})
export class DatasetService {
    constructor(private http: DataHttpService) {
    }
    static url = environment.apiUrlBack + '/datasets';

    getDatasets(page, number: number) {
        return this.http.get(`${DatasetService.url}` + '?page=' + page + '&size=' + number).map(res => res.valueOf());
    }

    postDataset(selectedDataset: DatasetRegisterDTO) {
        return this.http.post(`${DatasetService.url}`, selectedDataset).map(res => res.valueOf());

    }

    getDatasetById(id: string) {
        return this.http.get(`${DatasetService.url}` + '?id=' + id).map(res => res.valueOf());
    }

    postSampleInDataset(selectedSample: SampleRegisterDTO, datasetId: string) {
        return this.http.post(`${DatasetService.url}` + '/' + datasetId + '/samples', selectedSample).map(res => res.valueOf());
    }

    deleteDataset(id: string) {
        return this.http.delete(`${DatasetService.url}` + '?id=' + id).map(res => res.valueOf());
    }

    downloadDataset(id: string) {
        return this.http.get(`${DatasetService.url}` + '/' + id + '/download', {responseType: 'blob'}).map(res => res.valueOf());
    }

    putDataset(selectedDataset: DatasetRegisterDTO) {
        return this.http.put(`${DatasetService.url}`, selectedDataset).map(res => res.valueOf());

    }
}
