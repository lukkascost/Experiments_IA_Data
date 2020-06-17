import {Component, OnInit, ViewChild} from '@angular/core';
import {DatasetRegisterDTO} from "../../core/models/DatasetDTO";
import {SampleRegisterDTO} from "../../core/models/SampleDTO";
import {ExtractorType} from "../../core/models/enums/ExtractorType";
import {DatatableComponent} from "@swimlane/ngx-datatable";
import {isNullOrUndefined} from "util";
import {AttributesRegisterDTO} from "../../core/models/AttributesDTO";
import {DatasetService} from "../../shared/services/dataset.service";

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
  public ExtractorType = ExtractorType;
  extractorType;


  constructor(private datasetService: DatasetService) { }

  ngOnInit() {
    this.extractorType = ExtractorType.GLCM;
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

  keys(Enumerator): Array<string> {
    const keys = Object.keys(Enumerator);
    return keys.slice(keys.length / 2);
  }

  checkParams() {
    if (this.showdata.length == 0) return true;
    if (isNullOrUndefined(this.selectedDataset.description)) return true;
    if (isNullOrUndefined(this.selectedDataset.name)) return true;
    return false;
  }

  save() {
    var dataset = new DatasetRegisterDTO();
    dataset.name = this.selectedDataset.name;
    dataset.description = this.selectedDataset.description;
    dataset.samples = [];
    var counter = 1;
    this.showdata.forEach(x=>{
      var sampleDTO = new SampleRegisterDTO();
      sampleDTO.label = x[x.length-1];
      sampleDTO.extractorType = this.extractorType;
      sampleDTO.attributes = [];
      sampleDTO.originalFileName = "IMPORTED_FILE_"+counter+".txt";
      counter++;
      for (let i = 0; i < x.length-1; i++) {
        var attribute = new AttributesRegisterDTO();
        attribute.order = i;
        attribute.name = "Attribute "+(i+1);
        attribute.value = x[i];
        sampleDTO.attributes.push(attribute);
      }
      dataset.samples.push(sampleDTO);
    });
    this.datasetService.postDataset(dataset).toPromise().then(data=>{
      this.showdata = [];
      this.selectedDataset = new DatasetRegisterDTO();
    });
  }
}
