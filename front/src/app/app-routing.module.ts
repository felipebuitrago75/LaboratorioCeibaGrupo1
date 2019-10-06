import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LayoutComponent } from './layout/layout.component';
import { BooksComponent } from './books/books.component';
import { LoginComponent } from './login/login.component';
import { CreateBooksComponent } from './create-books/create-books.component';

const routes: Routes = [  
  { path: "", component: LoginComponent },
  {
    path: "",
    component: LayoutComponent,
    children: [
      { path: "books", component: BooksComponent },
      { path: "books-form", component: CreateBooksComponent }      
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
