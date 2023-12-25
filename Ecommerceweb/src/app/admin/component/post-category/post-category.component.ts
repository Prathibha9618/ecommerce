import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-post-category',
  templateUrl: './post-category.component.html',
  styleUrls: ['./post-category.component.scss']
})
export class PostCategoryComponent {

  categoryForm!: FormGroup;
  constructor(
    private fb:FormBuilder,
    private router:Router,
    private snackbar:MatSnackBar,
    private adminService:AdminService

  ){}
  ngOnInit():void{
    console.log('PostCategoryComponent initialized');
    this.categoryForm = this.fb.group({
      name:[null,[Validators.required]],
      description:[null,[Validators.required]],
    })

  }
  adminCategory():void{
    console.log('Entering adminCategory method');
    if(this.categoryForm.valid){

      this.adminService.addCategory(this.categoryForm.value).subscribe((res)=>{
        console.log('HTTP request successful:', res);

        if(res.id!=null){
          console.log("enter res");
          this.snackbar.open('category posted successfully!','close',{duration:5000});
          this.router.navigateByUrl('/admin/dashboard');
          console.log('Navigating to /admin/dashboard');
        }

        else{
          (error) => {
            console.error('HTTP request error:', error);
          }
          this.snackbar.open(res.message,'close',{
            duration:5000,
            panelClass:'error-snackbar'
          });
        }
      })
    }else{
      this.categoryForm.markAllAsTouched();
    }
  }

}
