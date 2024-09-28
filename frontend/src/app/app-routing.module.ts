import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { FilmListComponent } from './components/film-list/film-list.component';
import { PanierComponent } from './components/panier/panier.component';
import { AchatComponent } from './components/achat/achat.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'films', component: FilmListComponent },
  { path: 'panier', component: PanierComponent },
  { path: 'achat', component: AchatComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
