import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HandleErrorService} from '../../../shared/services/handle-error.service';
import {ExperimentsService} from '../../../shared/services/experiments.service';
import {ExperimentListDTO} from '../../../core/models/ExperimentsDTO';
import {PageExperimentsImpl} from '../../../core/models/Page';

@Component({
  selector: 'app-experiment-info',
  templateUrl: './experiment-info.component.html'
})
export class ExperimentInfoComponent implements OnInit {
  public id: string;
  experiment: ExperimentListDTO;


  constructor(private route: ActivatedRoute,
              private handleService: HandleErrorService,
              private experimentsService: ExperimentsService,
              private router: Router) { }

  ngOnInit() {
    this.id = this.route.snapshot.params.id;
    this.experiment = new ExperimentListDTO();
    this.experimentsService.getById(this.id).toPromise().then(data => {
      const result = (<PageExperimentsImpl>data).content[0];
      this.experiment = <ExperimentListDTO> result;
    });
  }
  getWithLineBreak(description: string) {
    return description.split(/\r\n|\r|\n/).length;
  }
}
