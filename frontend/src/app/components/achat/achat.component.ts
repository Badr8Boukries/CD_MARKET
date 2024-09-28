// src/app/components/achat/achat.component.ts
import { Component, OnInit } from '@angular/core';
import { FilmService } from '../../services/film.service';
import { Achat } from '../../models/achat.model';

@Component({
  selector: 'app-achat',
  templateUrl: './achat.component.html',
  styleUrls: ['./achat.component.css']
})
export class AchatComponent implements OnInit {
  achats: Achat[] = [];

  constructor(private filmService: FilmService) { }

  ngOnInit(): void {
    this.getAchats();
  }

  getAchats(): void {
    this.filmService.getAchats().subscribe(data => {
      this.achats = data;
    }, error => {
      console.error('Erreur lors de la récupération des achats', error);
      alert('Erreur lors de la récupération des achats.');
    });
  }
}
