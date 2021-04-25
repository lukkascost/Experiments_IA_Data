import {Component, OnDestroy, OnInit} from '@angular/core';
import {DatasetListDTO} from '../../core/models/DatasetDTO';
import {HandleErrorService} from '../../shared/services/handle-error.service';
import {Router} from '@angular/router';
import {DatasetService} from '../../shared/services/dataset.service';
import {Observable} from 'rxjs';
import {PageDatasetDTOImpl} from '../../core/models/Page';

@Component({
    selector: 'app-datasets',
    templateUrl: './datasets.component.html'
})
export class DatasetsComponent implements OnInit, OnDestroy {
    items: PageDatasetDTOImpl;
    public isLoading = false;
    public observer;
    currentPage = 0;


    constructor(private handleService: HandleErrorService,
                private datasetService: DatasetService,
                private router: Router) { }

    ngOnInit() {
        this.isLoading = true;
        this.items = new PageDatasetDTOImpl();
        this.currentPage = 0;
        this.getDatasets();
    }

    ngOnDestroy() {
        this.observer.unsubscribe();

    }

    getDatasets() {
        this.refreshDatasets(this.currentPage);
    }

    refreshDatasets(page) {
        this.datasetService.getDatasets(page, 10).toPromise()
            .then(
                data => {
                    this.items = (<PageDatasetDTOImpl>data);
                    this.isLoading = false;
                }
            ).catch(err => {
            this.handleService.handle(err);
            this.isLoading = false;
        });
        this.observer = Observable.timer(60000).first().subscribe(() => this.getDatasets());
    }

    pageChange(val: number) {
        this.currentPage = val - 1;
        this.refreshDatasets(val - 1);
    }
}
