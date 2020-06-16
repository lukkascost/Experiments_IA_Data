import {Component, OnInit, ViewChild} from '@angular/core';
import {DatasetRegisterDTO} from "../../core/models/DatasetDTO";
import {SampleRegisterDTO} from "../../core/models/SampleDTO";
import {ExtractorType} from "../../core/models/enums/ExtractorType";
import {DatatableComponent} from "@swimlane/ngx-datatable";

@Component({
  selector: 'app-import',
  templateUrl: './import.component.html'
})
export class ImportComponent implements OnInit {
  selectedDataset: DatasetRegisterDTO;
  file: string;
  showdata = [];
  showDatabool = false;
  pageSize: number;
  currentPage: number;
  @ViewChild(DatatableComponent, null) table: DatatableComponent;


  constructor() { }

  ngOnInit() {
    this.currentPage = 1;
    this.pageSize = 10;
    this.selectedDataset = new DatasetRegisterDTO();
    this.showdata = [];
    setTimeout(() => {
      window.dispatchEvent(new Event('resize'));
    }, 500);
  }

  onFileChange(event: Event) {
    let target: DataTransfer;
    target = <DataTransfer><unknown>(event.target);
    if (target.files.length !== 1) throw new Error('Cannot use multiple files');
    const reader: FileReader = new FileReader();
    reader.onload = (e: any) => {
      const bstr: string = e.target.result;
      const datas = bstr.split("\n");
      if(datas.length  == 0 )throw new Error("Empty file");
      datas.forEach(sample => {
        const attributes = sample.split(",");
        if(attributes.length  != 25 )return;
        this.showdata.push(attributes);
      })
      this.showDatabool = true;
      setTimeout(() => {
        this.table.limit = 10;
      }, 500);
    }
    reader.readAsBinaryString(target.files[0]);
  }

  onPageChanged(pageNum: number) {
    this.table.limit = 10;
    this.currentPage = pageNum;
    this.table.offset = pageNum - 1;
  }
}
