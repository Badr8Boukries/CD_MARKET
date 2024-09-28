import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';  // Import du HttpClientModule

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { FilmListComponent } from './components/film-list/film-list.component';
import { PanierComponent } from './components/panier/panier.component';
import { AchatComponent } from './components/achat/achat.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FilmListComponent,
    PanierComponent,
    AchatComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    // Ajout du module ici
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
