import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HandleErrorService} from '../../../shared/services/handle-error.service';
import {DatasetService} from '../../../shared/services/dataset.service';
import {DatasetListDTO, DatasetRegisterDTO} from '../../../core/models/DatasetDTO';
import {SampleListDTO} from '../../../core/models/SampleDTO';

@Component({
    selector: 'app-dataset-info',
    templateUrl: './dataset-info.component.html'
})
export class DatasetInfoComponent implements OnInit {
    public id: string;
    public isLoading: boolean;
    item: DatasetRegisterDTO;
    samples: SampleListDTO[];
    dataset: DatasetRegisterDTO;
    attributes: any[];
    description: any;

    constructor(private route: ActivatedRoute,
                private handleService: HandleErrorService,
                private datasetService: DatasetService,
                private router: Router) { }

    ngOnInit() {
        this.id = this.route.snapshot.params.id;
        this.isLoading = true;
        this.item = new DatasetRegisterDTO();
        this.getSamples();
        this.generateGraphics();
        this.datasetService.getDatasetById(this.id).toPromise().then(data=>{
            this.dataset = <DatasetRegisterDTO>data;
        });
    }

    getSamples() {
        this.isLoading = false;
        this.datasetService.getSamplesByDatasetId(this.id).toPromise()
            .then(
                data => {
                    this.samples = (<SampleListDTO[]>data);
                    this.isLoading = false;
                }
            ).catch(err => {
            this.handleService.handle(err);
            this.isLoading = false;
        });
    }

    getAttributes() {
        this.datasetService.getDatasetById(this.id).toPromise()
            .then(
                data => {
                    this.attributes = (<DatasetRegisterDTO>data).samples.map(x => x.attributes);
                }
            ).catch(err => {
            this.handleService.handle(err);
        });
    }

    private generateGraphics() {
    }

    getWithLineBreak(description: string) {
        return description.split(/\r\n|\r|\n/).length;
    }
}
