import { Routes } from '@angular/router';
import { FilmListComponent } from './components/film-list/film-list.component'; // Assure-toi que ces composants existent
import { PanierComponent } from './components/panier/panier.component';
import { AchatComponent } from './components/achat/achat.component';

export const routes: Routes = [
  { path: '', redirectTo: '/films', pathMatch: 'full' },
  { path: 'films', component: FilmListComponent },
  { path: 'panier', component: PanierComponent },
  { path: 'achat', component: AchatComponent },
];
