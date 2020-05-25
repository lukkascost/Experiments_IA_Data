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
  private id: string;
  private isLoading: boolean;
  item: DatasetRegisterDTO;
  samples: SampleListDTO[];

  constructor(private route: ActivatedRoute,
              private handleService: HandleErrorService,
              private datasetService: DatasetService,
              private router: Router) { }

  ngOnInit() {
    this.id = this.route.snapshot.params.id;
    this.isLoading = true;
    this.item = new DatasetRegisterDTO();
    this.getSamples();

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
}
