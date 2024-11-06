import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-panier-confirmation',
  templateUrl: './panier-confirmation.component.html'
})
export class PanierConfirmationComponent {

  constructor(
    public dialogRef: MatDialogRef<PanierConfirmationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { filmTitre: string },
    private router: Router
  ) {}

  // Redirection vers le panier
  goToCart(): void {
    this.dialogRef.close();
    this.router.navigate(['/panier']);
  }

  // Retour à la navigation
  close(): void {
    this.dialogRef.close();
  }
}
