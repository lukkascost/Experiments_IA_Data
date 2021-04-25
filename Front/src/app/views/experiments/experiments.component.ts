import {Component, OnDestroy, OnInit} from '@angular/core';
import {PageDatasetDTOImpl, PageExperimentsImpl} from '../../core/models/Page';
import {HandleErrorService} from '../../shared/services/handle-error.service';
import {DatasetService} from '../../shared/services/dataset.service';
import {Router} from '@angular/router';
import {ExperimentsService} from '../../shared/services/experiments.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-experiments',
  templateUrl: './experiments.component.html'
})
export class ExperimentsComponent implements OnInit, OnDestroy {
  items: PageExperimentsImpl;
  public isLoading = false;
  public observer;
  currentPage = 0;


  constructor(private handleService: HandleErrorService,
              private experimentsService: ExperimentsService,
              private router: Router) { }

  ngOnInit() {
    this.isLoading = true;
    this.items = new PageExperimentsImpl();
    this.currentPage = 0;
    this.get();
  }

  ngOnDestroy() {
    this.observer.unsubscribe();

  }


  get() {
    this.refresh(this.currentPage);
  }

  refresh(page) {
    this.experimentsService.get(page, 10).toPromise()
        .then(
            data => {
              this.items = (<PageExperimentsImpl>data);
              this.isLoading = false;
            }
        ).catch(err => {
      this.handleService.handle(err);
      this.isLoading = false;
    });
    this.observer = Observable.timer(60000).first().subscribe(() => this.get());
  }
  pageChange(val: number) {
    this.currentPage = val - 1;
    this.refresh(val - 1);
  }

}
