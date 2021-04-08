import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HandleErrorService} from '../../../shared/services/handle-error.service';
import {ExperimentsService} from '../../../shared/services/experiments.service';
import {ExperimentListDTO} from '../../../core/models/ExperimentsDTO';
import {PageExperimentsImpl, PageRoundsImpl} from '../../../core/models/Page';
import {RoundsService} from '../../../shared/services/rounds.service';
import {RoundsListDTO} from '../../../core/models/RoundsDTO';

@Component({
  selector: 'app-experiment-info',
  templateUrl: './experiment-info.component.html'
})
export class ExperimentInfoComponent implements OnInit {
  public id: string;
  experiment: ExperimentListDTO;
  rounds =  [];


  constructor(private route: ActivatedRoute,
              private handleService: HandleErrorService,
              private experimentsService: ExperimentsService,
              private roundsService: RoundsService,
              private router: Router) { }

  ngOnInit() {
    this.id = this.route.snapshot.params.id;
    this.experiment = new ExperimentListDTO();
    this.experimentsService.getById(this.id).toPromise().then(data => {
      const result = (<PageExperimentsImpl>data).content[0];
      this.experiment = <ExperimentListDTO> result;
    });

    this.roundsService.getByExperimentId(this.id).toPromise().then(data => {
      const result = (<PageRoundsImpl>data).content;
      this.rounds = <RoundsListDTO[]>result;
    });
  }
  getWithLineBreak(description: string) {
    return description.split(/\r\n|\r|\n/).length;
  }
}
