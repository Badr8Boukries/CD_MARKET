// src/app/models/film.model.ts
// src/app/models/film.model.ts
export class Film {
  id: number;
  titre: string;
  description: string;
  prix: number;
  urlImage: string;

  constructor(id: number, titre: string, description: string, prix: number, urlImage: string) {
    this.id = id;
    this.titre = titre;
    this.description = description;
    this.prix = prix;
    this.urlImage = urlImage;
  }
}

