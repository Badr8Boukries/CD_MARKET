import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule,provideHttpClient,withFetch  } from '@angular/common/http';  // Import du HttpClientModule
import { MatDialogModule } from '@angular/material/dialog';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { FilmListComponent } from './components/film-list/film-list.component';
import { PanierComponent } from './components/panier/panier.component';
import { AchatComponent } from './components/achat/achat.component';
import {FormsModule} from "@angular/forms";
import { PanierConfirmationComponent } from './panier-confirmation/panier-confirmation.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FilmListComponent,
    PanierComponent,
    AchatComponent,
    PanierConfirmationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatDialogModule


  ],
  providers: [
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
