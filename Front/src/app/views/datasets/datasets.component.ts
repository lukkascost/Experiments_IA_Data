import {Component, OnDestroy, OnInit} from '@angular/core';
import {DatasetListDTO} from '../../core/models/DatasetDTO';
import {HandleErrorService} from '../../shared/services/handle-error.service';
import {Router} from '@angular/router';
import {DatasetService} from '../../shared/services/dataset.service';

@Component({
  selector: 'app-datasets',
  templateUrl: './datasets.component.html'
})
export class DatasetsComponent implements OnInit, OnDestroy{
  items: DatasetListDTO[];
  public isLoading = false;


  constructor(private handleService: HandleErrorService,
              private datasetService: DatasetService,
              private router: Router) { }

  ngOnInit() {
    this.isLoading = true;
    this.getDatasets();
  }

  ngOnDestroy() {
  }

  private getDatasets() {
    this.isLoading = false;
    this.datasetService.getDatasets().toPromise()
        .then(
            data => {
              this.items = (<DatasetListDTO[]>data);
              this.isLoading = false;
            }
        ).catch(err => {
      this.handleService.handle(err);
      this.isLoading = false;
    });
  }
}
