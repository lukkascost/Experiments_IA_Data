import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChange, SimpleChanges, ViewChild} from '@angular/core';
import {ISampleDTO} from '../../../core/models/SampleDTO';
import {DatatableComponent} from '@swimlane/ngx-datatable';
import {IAttributeDTO} from '../../../core/models/AttributesDTO';

@Component({
  selector: 'app-attributes-table',
  templateUrl: './attributes-table.component.html'
})
export class AttributesTableComponent implements OnInit, OnChanges  {
  @Input() data: IAttributeDTO[][] = [];
  @Input() isLoading = true;
  @Input() datasetId: string;
  @Output() refreshData = new EventEmitter();
  @Output() dataChange = new EventEmitter();
  @ViewChild(DatatableComponent, null) table: DatatableComponent;
  pageSize: number;
  currentPage: number;

  constructor() { }

  ngOnInit() {
    this.currentPage = 1;
    this.pageSize = 10;
    this.table.limit = 10;
    setTimeout(() => {
      window.dispatchEvent(new Event('resize'));
    }, 500);
  }
  ngOnChanges(changes: SimpleChanges) {
    const currentItem: SimpleChange = changes.item;
    if (currentItem === undefined) { return; }
    console.log('prev value: ', currentItem.previousValue);
    console.log('got item: ', currentItem.currentValue);
  }

  onPageChanged(pageNum) {
    this.currentPage = pageNum;
    this.table.offset = pageNum - 1;
  }

}
