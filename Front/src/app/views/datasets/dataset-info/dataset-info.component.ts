import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HandleErrorService} from '../../../shared/services/handle-error.service';
import {DatasetService} from '../../../shared/services/dataset.service';
import {DatasetListDTO, DatasetRegisterDTO} from '../../../core/models/DatasetDTO';
import {SampleListDTO} from '../../../core/models/SampleDTO';
import {SampleService} from '../../../shared/services/sample.service';
import {PageDatasetDTOImpl, PageSampleImpl} from '../../../core/models/Page';

@Component({
    selector: 'app-dataset-info',
    templateUrl: './dataset-info.component.html'
})
export class DatasetInfoComponent implements OnInit {
    public id: string;
    public isLoading: boolean;
    item: DatasetListDTO;
    samples: PageSampleImpl;
    dataset: DatasetListDTO;
    attributes: any[];
    description: any;

    constructor(private route: ActivatedRoute,
                private handleService: HandleErrorService,
                private datasetService: DatasetService,
                private sampleService: SampleService,
                private router: Router) { }

    ngOnInit() {
        this.samples = new PageSampleImpl();
        this.dataset = new DatasetListDTO();
        this.id = this.route.snapshot.params.id;
        this.isLoading = true;
        this.item = new DatasetListDTO();
        this.getSamples();
        this.generateGraphics();

    }

    getSamples() {
        this.datasetService.getDatasetById(this.id).toPromise().then(data => {
            const result = (<PageDatasetDTOImpl>data).content[0];
            this.dataset = <DatasetListDTO> result;
        });
        this.isLoading = true;
        this.sampleService.getSamplesByDatasetId(this.id, 0, 10).toPromise()
            .then(
                data => {
                    this.samples = (<PageSampleImpl>data);
                    this.isLoading = false;
                }
            ).catch(err => {
            this.handleService.handle(err);
            this.isLoading = false;
        });
    }

    refreshSamples(page) {
        this.isLoading = true;
        this.sampleService.getSamplesByDatasetId(this.id, page, 10).toPromise()
            .then(
                data => {
                    this.samples = (<PageSampleImpl>data);
                    this.isLoading = false;
                }
            ).catch(err => {
            this.handleService.handle(err);
            this.isLoading = false;
        });
    }

    private generateGraphics() {
    }

    getWithLineBreak(description: string) {
        return description.split(/\r\n|\r|\n/).length;
    }

    pageChange($event: number) {
        console.log($event);
        this.refreshSamples($event - 1);
    }
}
