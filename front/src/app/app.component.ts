import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { TranslateService } from "@ngx-translate/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent implements OnInit {
  public myForm: FormGroup;

  constructor(private translate: TranslateService, private router: Router) {}

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
    console.log('tran');
    this.router.navigate(["/books"]);
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
