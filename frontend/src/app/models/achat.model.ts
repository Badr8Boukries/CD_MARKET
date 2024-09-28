// src/app/models/achat.model.ts
import { Film } from './film.model';

export interface Achat {
  id: number;
  films: Film[];
  dateAchat: Date;
}
