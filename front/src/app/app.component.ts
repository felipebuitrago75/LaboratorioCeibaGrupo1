import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent implements OnInit {
  public myForm: FormGroup;

  ngOnInit() {
    this.myForm = new FormGroup({
      name: new FormGroup({
        firstName: new FormControl("", Validators.required),
        lastName: new FormControl("", Validators.required)
      }),
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
  login(){
    let controls = this.myForm.controls;
    if (this.myForm.invalid) {
			Object.keys(controls).forEach(controlName =>
				controls[controlName].markAsTouched()
			);
			return;
    }
    
    const authData = {
			userName: controls['email'].value,
			password: controls['password'].value
    };
    
    console.log(authData);
  }
}
