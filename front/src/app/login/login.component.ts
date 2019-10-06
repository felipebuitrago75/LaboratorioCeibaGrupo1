import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';
import { RestService } from '../services/rest.service';
import { BooksComponent } from '../books/books.component';
import swal from "sweetalert2";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public myForm: FormGroup;

  constructor(private translate: TranslateService, private router: Router,private service:RestService) {}

  ngOnInit() {
    this.myForm = new FormGroup({
      email: new FormControl("", [
        Validators.required        
      ]),
      password: new FormControl("", [
        Validators.required,
        Validators.minLength(4)
      ]),
      language: new FormControl()
    });
  }

  /**
   * Performs the login action
   *
   *
   */
  login() {
    let controls = this.myForm.controls;
    if (this.myForm.invalid) {
      Object.keys(controls).forEach(controlName =>
        controls[controlName].markAsTouched()
      );
      return;
    }

    const authData = {
      email: controls["email"].value,
      password: controls["password"].value
    };
    let email = controls["email"].value;
    let password = controls["password"].value;
    let url = `validarUsuario?nombreUsuario=${email}&contrasenia=${password}`;         
     this.service.queryExternalApi(url).subscribe(
      response => {
        let result = response.json();
        if (result) {          
          this.router.navigate(["/books"]);        
        } else {          
          swal({
            title: this.translate.instant("alerts.error"),
            text: this.translate.instant("alerts.not_login"),
            type: "error",
            showCancelButton: false,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: this.translate.instant("buttons.ok"),            
          }).then(result => {
            return false;
          });
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
  controlHasError(controlName: string, validationType: string): boolean {
    const control = this.myForm.controls[controlName];
    
    if (!control) {
      return false;
    }

    const result =
      control.hasError(validationType) && (control.dirty || control.touched);      
      
    return result;   
  }

}
