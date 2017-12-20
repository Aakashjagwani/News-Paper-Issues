import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { AppComponent } from './app.component';
import { NewsPaperComponent }  from './newspaper.component';
import { NewspaperService } from './newspaper.service';


@NgModule({
  declarations: [
    AppComponent,
    NewsPaperComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    CommonModule
  ],
  providers: [
    NewspaperService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
