import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {DatasetService} from '../../services/dataset.service';
import {ISampleDTO, SampleListDTO, SampleRegisterDTO} from '../../../core/models/SampleDTO';
import {ExtractorType} from '../../../core/models/enums/ExtractorType';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import {SampleService} from '../../services/sample.service';
import {PageSampleImpl} from '../../../core/models/Page';

@Component({
  selector: 'app-sample-table',
  templateUrl: './sample-table.component.html'
})
export class SampleTableComponent implements OnInit {

  @Input() data: PageSampleImpl;
  @Input() isLoading = true;
  @Input() datasetId: string;
  @Output() refreshData = new EventEmitter();
  @Output() dataChange: EventEmitter<PageSampleImpl> = new EventEmitter<PageSampleImpl>();
  @Output() pageChange = new EventEmitter();
  @ViewChild(DatatableComponent, null) table: DatatableComponent;

  private selectedSample: SampleRegisterDTO;
  public ExtractorType = ExtractorType;

  constructor(
      private modalService: NgbModal,
      private datasetService: DatasetService,
      private sampleService: SampleService
  ) { }

  ngOnInit() {
    this.table.limit = 9;
    this.selectedSample = new SampleRegisterDTO();
    setTimeout(() => {
      window.dispatchEvent(new Event('resize'));
    }, 500);
  }

  onPageChanged(pageNum) {
    this.pageChange.emit(pageNum);
  }
  clean() {
    this.selectedSample = new SampleRegisterDTO();
  }

  open(content) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' })
        .result.then((result) => {
      console.log(result);
    }, (reason) => {
      console.log('Err!', reason);
    });
  }

  keys(Enumerator): Array<string> {
    const keys = Object.keys(Enumerator);
    return keys.slice(keys.length / 2);
  }

  addNew() {
    this.datasetService.postSampleInDataset(this.selectedSample, this.datasetId).toPromise()
        .then(
            data => {
              this.refreshData.emit();
            }
        ).catch(err => {
    });
  }

  delete(row: SampleListDTO) {
    this.sampleService.deleteSample(row.id).toPromise()
        .then(
            data => {
              this.refreshData.emit();
            }
        ).catch(err => {
    });
  }
}
