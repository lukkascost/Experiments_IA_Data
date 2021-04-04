import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild} from '@angular/core';
import {DatasetListDTO, DatasetRegisterDTO, IDatasetDTO} from '../../../core/models/DatasetDTO';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {DatasetService} from '../../services/dataset.service';
import {DatatableComponent} from '@swimlane/ngx-datatable';
import {SampleRegisterDTO} from '../../../core/models/SampleDTO';
import {PageDatasetDTOImpl, PageExperimentsImpl} from '../../../core/models/Page';
import {ExperimentRegisterDTO} from '../../../core/models/ExperimentsDTO';
import {ExperimentsService} from '../../services/experiments.service';

@Component({
    selector: 'app-experiments-table',
    templateUrl: './experiments-table.component.html'
})
export class ExperimentsTableComponent implements OnInit , OnChanges {
    @Input() data: PageExperimentsImpl;
    @Input() loading: boolean;
    @Output() refreshData = new EventEmitter();
    @Output() dataChange = new EventEmitter();
    @Output() pageChange = new EventEmitter();

    @ViewChild(DatatableComponent, null) table: DatatableComponent;
    rows = null;
    refreshRows = true;

    private experimentRegisterDTO: ExperimentRegisterDTO;
    constructor(
        private modalService: NgbModal,
        private experimentsService: ExperimentsService
    ) { }

    ngOnChanges(changes: SimpleChanges) {
        if (changes.data) {
            this.rows  = changes.data.currentValue;
        }
    }

    ngOnInit() {
        this.experimentRegisterDTO = new ExperimentRegisterDTO();
        setTimeout(() => {
            window.dispatchEvent(new Event('resize'));
        }, 500);
    }


    clean() {
        this.experimentRegisterDTO = new ExperimentRegisterDTO();
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
        this.experimentsService.create(this.experimentRegisterDTO).toPromise()
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

    delete(row: ExperimentRegisterDTO) {
        this.experimentsService.delete(row.id).toPromise()
            .then(
                data => {
                    this.refreshData.emit();
                    this.refreshRows = true;
                }
            ).catch(err => {
        });
    }

    openEdit(row) {
        this.experimentRegisterDTO.description =  (<ExperimentRegisterDTO>row).description;
        this.experimentRegisterDTO.name =  (<ExperimentRegisterDTO>row).name;
    }

    edit() {
        this.experimentsService.update(this.experimentRegisterDTO).toPromise()
            .then(
                data => {
                    this.refreshData.emit();
                }
            ).catch(err => {
        });
    }
}
