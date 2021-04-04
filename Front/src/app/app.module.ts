import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CoreModule} from './core/core.module';
import {SecurityModule} from './security/security.module';
import localePt from '@angular/common/locales/pt';
import {registerLocaleData} from '@angular/common';
import { ExperimentsComponent } from './views/experiments/experiments.component';


registerLocaleData(localePt, 'pt-BR');

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        CoreModule,
        SecurityModule,
    ],
    providers: [{provide: LOCALE_ID, useValue: 'pt-BR'}],
    bootstrap:
        [AppComponent]
})

export class AppModule {
}
