import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-postproduct',
  templateUrl: './postproduct.component.html',
  styleUrls: ['./postproduct.component.scss']
})
export class PostproductComponent {

  productForm: FormGroup;
  listOfCategories:any=[];
  selectedFile:File|null;
  imagePreview:string|ArrayBuffer|null;

  constructor(
    private fb:FormBuilder,
    private router:Router,
    private snackBar:MatSnackBar,
    private adminservice:AdminService
  ){}

  onFileSelected(event:any){
    this.selectedFile= event.target.files[0];
    this.previewImage();
  }
  previewImage(){
    const reader = new FileReader();
    reader.onload =() =>{
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }
  ngOnInit():void{
    this.productForm = this.fb.group({
      categoryId:[null,[Validators.required]],
      name:[null,[Validators.required]],
      price:[null,[Validators.required]],
      description:[null,[Validators.required]],
    });
   this.getAllCategories();
  }
  getAllCategories(){
    this.adminservice.getAllCategories().subscribe(res=>{
      console.log("getallcategory"+res)
      this.listOfCategories = res;
    })
  }
  addProduct():void{
    if(this.productForm.valid){
      const formData:FormData = new FormData();
      formData.append('img',this.selectedFile);
      formData.append('categoryId',this.productForm.get('categoryId').value);
      formData.append('name',this.productForm.get('name').value);
      formData.append('price',this.productForm.get('price').value);
      formData.append('description',this.productForm.get('description').value);

      console.log('FormData:', formData); // Log the FormData before sending


      this.adminservice.addProduct(formData).subscribe((res)=>{
        console.log('Response:', res); // Log the server response

        if(res.id != null){
          this.snackBar.open('product posted successfully','close',{duration:500});
          this.router.navigateByUrl("/admin/dashboard");
        }else{
          this.snackBar.open(res.message,'ERROR',{duration:5000});
        }
      })
    }else{
      for(const i in this.productForm.controls){
        this.productForm.controls[i].markAsDirty();
        this.productForm.controls[i].updateValueAndValidity();
      }
    }
  }

}
