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
      this.getCmOrdererFromRound(this.rounds[0]);
    });
  }
  getWithLineBreak(description: string) {
    return description.split(/\r\n|\r|\n/).length;
  }

  private getCmOrdererFromRound(round) {
    const result = {};
    round.labels.forEach(x =>
        result[x] = round.labels.indexOf(x)
    );
    return result;
  }


  getLabelsOrdered(round) {
    const myClonedObject = Object.assign([], round.labels);
    return myClonedObject.sort();
  }

  getFromRowMatrix(row: any, cmOrdererFromRoundElement: any, cmOrdererFromRoundElement2: any, max) {
    let total = 0;
    for (let i = 0; i < row.length; i++) {
      total += row[i][cmOrdererFromRoundElement2];
    }
    return Math.fround((row[cmOrdererFromRoundElement][cmOrdererFromRoundElement2] * 100 ) / total).toPrecision(4);
    // return Math.fround(row[cmOrdererFromRoundElement][cmOrdererFromRoundElement2] );
  }
}
