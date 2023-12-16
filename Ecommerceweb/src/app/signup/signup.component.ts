import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../Services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {

  signupForm!: FormGroup;
  hidePassword= true;

  constructor( private fb:FormBuilder,
    private snackbar:MatSnackBar,
    private authService: AuthService,
    private router:Router){

    }

    ngOnInit():void{
      this.signupForm = this.fb.group({
        name:[null,[Validators.required]],
        email:[null,[Validators.required,Validators.email]],
        password:[null,[Validators.required]],
        confirmPassword:[null,[Validators.required]],
      })

    }
    togglePasswordVisibility(){
      this.hidePassword =!this.hidePassword;

    }
    onSubmit():void{
      const password = this.signupForm.get('password')?.value;
      const confirmPassword = this.signupForm.get('confirmPassword')?.value;

    if(password !== confirmPassword ){
      this.snackbar.open('passwords do not match','Close',{duration:5000,panelClass:'error-snackbar'});
      return;
    }
    this.authService.register(this.signupForm.value).subscribe(
      (Response) =>{
        this.snackbar.open('sign up successful','Close',{duration:5000});
        this.router.navigateByUrl("/login");

      },
      (error)=>{
        this.snackbar.open('sign up failed','Close',{duration:5000,panelClass:'error-snackbar'});

      }
    )

    }
  }


