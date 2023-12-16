import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../Services/auth/auth.service';
import { UserStorageService } from '../Services/auth/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginForm!: FormGroup;
  hidePassword= true;

  constructor( private fb:FormBuilder,
    private snackbar:MatSnackBar,
    private authService: AuthService,
    private router:Router){

    }

    ngOnInit():void{
      this.loginForm = this.fb.group({

        email:[null, [Validators.required,Validators.email]],
        password:[null, [Validators.required]],

      })

    }
    togglePasswordVisibility(){
      this.hidePassword =!this.hidePassword;

    }
    onSubmit():void{
      const username = this.loginForm.get('email')?.value;
      const password = this.loginForm.get('password')?.value;


    this.authService.login(username,password).subscribe(
      (res) =>{
       if(UserStorageService.isAdminLoggedIn()){
          this.router.navigateByUrl('admin/dashboard');

        }
        else if(UserStorageService.isCustomerLoggedIn()){
          this.router.navigateByUrl('customer/dashboard');
        }

      },
      (error)=>{
        this.snackbar.open('bad credentials','Error',{duration:5000});
      }
    )

    }
  }




