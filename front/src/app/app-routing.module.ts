import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LayoutComponent } from './layout/layout.component';
import { BooksComponent } from './books/books.component';

const routes: Routes = [  
  {
    path: "",
    component: LayoutComponent,
    children: [
      { path: "books", component: BooksComponent }      
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
