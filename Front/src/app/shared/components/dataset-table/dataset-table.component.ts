import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {DatasetListDTO, DatasetRegisterDTO, IDatasetDTO} from '../../../core/models/DatasetDTO';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {DatasetService} from '../../services/dataset.service';

@Component({
  selector: 'app-dataset-table',
  templateUrl: './dataset-table.component.html'
})
export class DatasetTableComponent implements OnInit {
  @Input() data: IDatasetDTO[] = [];
  @Output() refreshData = new EventEmitter();
  @Output() dataChange = new EventEmitter();
  private selectedDataset: DatasetRegisterDTO;
  constructor(
      private modalService: NgbModal,
      private datasetService: DatasetService
  ) { }

  ngOnInit() {
    setTimeout(() => {
      window.dispatchEvent(new Event('resize'));
    }, 500);
  }


  clean() {
    this.selectedDataset = new DatasetRegisterDTO();
  }
  open(content) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' })
        .result.then((result) => {
      console.log(result);
    }, (reason) => {
      console.log('Err!', reason);
    });
  }

  addNew() {
    this.datasetService.postDataset(this.selectedDataset).toPromise()
        .then(
            data => {
              this.refreshData.emit();
            }
        ).catch(err => {
    });
  }
}
