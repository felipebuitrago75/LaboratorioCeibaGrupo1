import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { TranslateService } from "@ngx-translate/core";
import { Router } from "@angular/router";
import { RestService } from "./services/rest.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent implements OnInit {
  public myForm: FormGroup;

  constructor(private translate: TranslateService, private router: Router,private service:RestService) {}

  ngOnInit() {
    this.myForm = new FormGroup({
      email: new FormControl("", [
        Validators.required,
        Validators.pattern("[^ @]*@[^ @]*")
      ]),
      password: new FormControl("", [
        Validators.required,
        Validators.minLength(8)
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
    console.log(url);
    this.service.queryExternalApi(url).subscribe(
      response => {
        let result = response.json();
        if (result) {
          console.log('autenticado!');
          //let token = "Bearer " + result.token;
          //localStorage.setItem("token", token);
          //localStorage.setItem("user", JSON.stringify(result.user));
        
        } else {
          /* this.toastr.error(result.message, "Error", {
            enableHtml: true,
            positionClass: "toast-top-center"
          }); */
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
  controlHasError(controlName: string, validationType: string): boolean {
    const control = this.myForm.controls[controlName];
    if (!control) {
      return false;
    }

    const result =
      control.hasError(validationType) && (control.dirty || control.touched);      
  }
}
