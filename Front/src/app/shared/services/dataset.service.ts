import {environment} from '../../../environments/environment';
import {Injectable} from '@angular/core';
import {DataHttpService} from '../../security/data-http.service';
import {DatasetRegisterDTO} from '../../core/models/DatasetDTO';
import {SampleRegisterDTO} from '../../core/models/SampleDTO';


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

    postDataset(selectedDataset: DatasetRegisterDTO) {
        return this.http.post(`${DatasetService.url}`, selectedDataset).map(res => res.valueOf());

    }

    getDatasetById(id: string) {
        return this.http.get(`${DatasetService.url}` + '/' + id).map(res => res.valueOf());
    }

    getSamplesByDatasetId(id: string) {
        return this.http.get(`${DatasetService.url}` + '/' + id + '/samples').map(res => res.valueOf());
    }

    postSampleInDataset(selectedSample: SampleRegisterDTO, datasetId: string) {
        return this.http.post(`${DatasetService.url}` + '/' + datasetId + '/samples', selectedSample).map(res => res.valueOf());
    }
}
