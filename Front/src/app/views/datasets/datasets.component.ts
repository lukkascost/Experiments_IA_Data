import {Component, OnDestroy, OnInit} from '@angular/core';
import {DatasetListDTO} from '../../core/models/DatasetDTO';
import {HandleErrorService} from '../../shared/services/handle-error.service';
import {Router} from '@angular/router';
import {DatasetService} from '../../shared/services/dataset.service';
import {Observable} from "rxjs";

@Component({
  selector: 'app-datasets',
  templateUrl: './datasets.component.html'
})
export class DatasetsComponent implements OnInit, OnDestroy{
  items: DatasetListDTO[];
  public isLoading = false;
  public observer;


  constructor(private handleService: HandleErrorService,
              private datasetService: DatasetService,
              private router: Router) { }

  ngOnInit() {
    this.isLoading = true;
    this.getDatasets();
  }

  ngOnDestroy() {
    this.observer.unsubscribe();

  }

  private getDatasets() {
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
    this.observer = Observable.timer(60000).first().subscribe(() => this.getDatasets());
  }
}
