import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild} from '@angular/core';
import {DatasetListDTO, DatasetRegisterDTO, IDatasetDTO} from '../../../core/models/DatasetDTO';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {DatasetService} from '../../services/dataset.service';
import {DatatableComponent} from '@swimlane/ngx-datatable';
import {SampleRegisterDTO} from '../../../core/models/SampleDTO';
import {PageDatasetDTOImpl} from '../../../core/models/Page';

@Component({
    selector: 'app-dataset-table',
    templateUrl: './dataset-table.component.html'
})
export class DatasetTableComponent implements OnInit , OnChanges {
    @Input() data: PageDatasetDTOImpl;
    @Input() loading: boolean;
    @Output() refreshData = new EventEmitter();
    @Output() dataChange = new EventEmitter();
    @Output() pageChange = new EventEmitter();

    @ViewChild(DatatableComponent, null) table: DatatableComponent;
    rows = null;
    refreshRows = true;

    private selectedDataset: DatasetRegisterDTO;
    constructor(
        private modalService: NgbModal,
        private datasetService: DatasetService
    ) { }

    ngOnChanges(changes: SimpleChanges) {
        if (changes.data) {
            this.rows  = changes.data.currentValue;
        }
    }

    ngOnInit() {
        this.selectedDataset = new DatasetRegisterDTO();
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
    onPageChanged(pageNum) {
        this.pageChange.emit(pageNum);
    }
    updateFilter(event) {
        const val = event.target.value.toLowerCase();

        // filter our data
        const temp = this.data.content.filter(function (d) {
            return d.name.toLowerCase().indexOf(val) !== -1 || !val;
        });

        // update the rows
        this.rows = temp;
        // Whenever the filter changes, always go back to the first page
        this.table.offset = 0;
    }

    delete(row: DatasetListDTO) {
        this.datasetService.deleteDataset(row.id).toPromise()
            .then(
                data => {
                    this.refreshData.emit();
                    this.refreshRows = true;
                }
            ).catch(err => {
        });
    }

    download(row) {
        this.datasetService.downloadDataset(row.id).toPromise()
            .then(data => {
                console.log(data);
                let url = window.URL.createObjectURL(data);
                let a = document.createElement('a');
                document.body.appendChild(a);
                a.setAttribute('style', 'display: none');
                a.href = url;
                a.download = row.name + '.txt';
                a.click();
                window.URL.revokeObjectURL(url);
                a.remove(); // remove the element
            })
            .catch(err => {
                console.log('download error:', JSON.stringify(err));
            });
    }

    openEdit(row) {
        this.selectedDataset.description =  (<DatasetListDTO>row).description;
        this.selectedDataset.name =  (<DatasetListDTO>row).name;
    }

    edit() {
        // console.log(this.selectedDataset)
        this.datasetService.putDataset(this.selectedDataset).toPromise()
            .then(
                data => {
                    this.refreshData.emit();
                }
            ).catch(err => {
        });
    }
}
