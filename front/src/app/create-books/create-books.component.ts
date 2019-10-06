import { Component, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { Router, ActivatedRoute } from "@angular/router";
import { RestService } from "../services/rest.service";
import { Book } from "../interfaces/book";
import { FormGroup, FormControl, Validators } from "@angular/forms";

@Component({
  selector: "app-create-books",
  templateUrl: "./create-books.component.html",
  styleUrls: ["./create-books.component.scss"]
})
export class CreateBooksComponent implements OnInit {
  public editing: boolean = false;
  private isbn: number = null;
  private name: string = null;
  private quantity: number = null;
  private availableQuantity: number = null;
  public myForm: FormGroup;
  constructor(
    private translate: TranslateService,
    private router: Router,
    private service: RestService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isbn = this.route.snapshot.queryParams["isbn"];
    this.name = this.route.snapshot.queryParams["name"];
    this.quantity = this.route.snapshot.queryParams["quantity"];
    this.availableQuantity = this.route.snapshot.queryParams["available_quantity"];

    this.myForm = new FormGroup({
      isbn: new FormControl("isbn", [Validators.required]),
      name: new FormControl("name", [Validators.required]),
      quantity: new FormControl("quantity", [Validators.required]),
      available_quantity: new FormControl("available_quantity", [Validators.required])
    });

    if (null !== this.isbn && undefined !== this.isbn) {
      this.myForm.controls["isbn"].setValue(this.isbn);
      this.editing = true;
    }

    if (null !== this.name && undefined !== this.name) {
      this.myForm.controls["name"].setValue(this.name);
    }

    if (null !== this.quantity && undefined !== this.quantity) {
      this.myForm.controls["quantity"].setValue(this.quantity);
    }

    if (null !== this.availableQuantity && undefined !== this.availableQuantity) {
      this.myForm.controls["available_quantity"].setValue(this.availableQuantity);
    }
  }

  /**
   * Stores or updates a book
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

    const bookData = {
      ISBN: controls["isbn"].value,
      NOMBRE: controls["name"].value,
      CANTIDAD_INVENTARIO: controls["quantity"].value,
      CANTIDAD_DISPONIBLE: controls["available_quantity"].value
    };
    
    let url = `agregarLibro`;
    this.service.queryPostRegular(url,bookData).subscribe(
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

    const result =
      control.hasError(validationType) && (control.dirty || control.touched);

      return result;
  }
}
