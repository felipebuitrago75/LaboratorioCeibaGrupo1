import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { RestService } from '../services/rest.service';

@Component({
  selector: 'app-lend-books',
  templateUrl: './lend-books.component.html',
  styleUrls: ['./lend-books.component.scss']
})
export class LendBooksComponent implements OnInit {
  public myForm: FormGroup;
  constructor(private service:RestService) { }

  ngOnInit() {
    this.myForm = new FormGroup({
      isbn: new FormControl("isbn", [Validators.required]),      
      person_name: new FormControl("person_name", [Validators.required])
    });
  }

  /**
   * Stores a book loan
   *
   *
   */
  store() {
    let controls = this.myForm.controls;
    if (this.myForm.invalid) {
      Object.keys(controls).forEach(controlName =>
        controls[controlName].markAsTouched()
      );
      return;
    }

    const loanData = {      
    };

    let url = `prestarLibro/${controls["isbn"].value}/${controls["person_name"].value}`;
    this.service.queryPostRegular(url,loanData).subscribe(
      response => {
        let result = response.json();
        if (result) {          
          //this.router.navigate(["/books"]);        
        } else {          
          console.log('error');
        }
      },
      err => {
        console.log(err);
      }
    );
  }

  /**
   * Validates whether a field follows the validation rules
   *
   * @param controlName name of the control being evaluated
   * @param validationType type of the validation to be evaluated
   */
  public controlHasError(controlName: string, validationType: string): boolean {
    const control = this.myForm.controls[controlName];
    if (!control) {
      return false;
    }

    const result = control.hasError(validationType) && (control.dirty || control.touched);

    return result;
  }



}
