import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-lend-books',
  templateUrl: './lend-books.component.html',
  styleUrls: ['./lend-books.component.scss']
})
export class LendBooksComponent implements OnInit {
  public myForm: FormGroup;
  constructor() { }

  ngOnInit() {
    this.myForm = new FormGroup({
      isbn: new FormControl("isbn", [Validators.required]),
      loan_date: new FormControl("loan_date", [Validators.required]),
      delivery_date: new FormControl("delivery_date", [Validators.required]),
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
      isbn: controls["isbn"].value,
      loanDate: controls["loan_date"].value,
      deliveryDate: controls["delivery_date"].value,
      personName: controls["person_name"].value
    };

    console.log(loanData);
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
  }

}
