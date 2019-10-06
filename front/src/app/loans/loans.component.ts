import { Component, OnInit } from '@angular/core';
import { Loan } from '../interfaces/loan';
import { RestService } from '../services/rest.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-loans',
  templateUrl: './loans.component.html',
  styleUrls: ['./loans.component.scss']
})
export class LoansComponent implements OnInit {
  public loans: Loan[] = [];
  constructor(private service:RestService, public translate: TranslateService) { }

  ngOnInit() {
    //this.getLoans();
  }

  /**
   * Gets the list of books in the inventory 
   *
   */
  private getLoans(){
    let url = `obtenerLibrosDisponibles`;
    this.service.queryExternalApi(url).subscribe(
      response => {
        let result = response.json();
        if (result) {
          this.loans = result;
        
        } else {          
          console.log('error');
        }
      },
      err => {
        console.log(err);
      }
    ); 
  }

}
