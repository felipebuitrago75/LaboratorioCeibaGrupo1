import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Router, ActivatedRoute } from '@angular/router';
import { RestService } from '../services/rest.service';
import { Book } from '../interfaces/book';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-books',
  templateUrl: './create-books.component.html',
  styleUrls: ['./create-books.component.scss']
})
export class CreateBooksComponent implements OnInit {
  public book: Book;
  public myForm: FormGroup;
  constructor(private translate: TranslateService, private router: Router,private service:RestService,private route: ActivatedRoute,) { }

  ngOnInit() {
    this.book = this.route.snapshot.params["book"];  

    this.myForm = new FormGroup({
      isbn: new FormControl("isbn", [
        Validators.required        
      ]),
      name: new FormControl("name", [
        Validators.required        
      ]),
      quantity: new FormControl("quantity", [
        Validators.required        
      ])      
    });

  }

  /**
   * Stores or updates a book
   * 
   * 
   */
  store(){
    let controls = this.myForm.controls;
    if (this.myForm.invalid) {
      Object.keys(controls).forEach(controlName =>
        controls[controlName].markAsTouched()
      );
      return;
    }

    const bookData = {      
			isbn: controls['isbn'].value,
      nombre: controls['name'].value,
      cantidadInventario: controls['quantity'].value
    };
    
    console.log(bookData);
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

    const result =
      control.hasError(validationType) && (control.dirty || control.touched);      
  }

}