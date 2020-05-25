import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IDatasetDTO} from '../../../core/models/DatasetDTO';

@Component({
  selector: 'app-dataset-table',
  templateUrl: './dataset-table.component.html'
})
export class DatasetTableComponent implements OnInit {
  @Input() data: IDatasetDTO[] = [];
  @Output() refreshData = new EventEmitter();
  constructor() { }

  ngOnInit() {
    setTimeout(() => {
      window.dispatchEvent(new Event('resize'));
    }, 500);
  }


}
