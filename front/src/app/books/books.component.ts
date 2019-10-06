import { Component, OnInit } from '@angular/core';
import { RestService } from '../services/rest.service';
import { Book } from '../interfaces/book';



@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss']
})
export class BooksComponent implements OnInit {

  public books: Book[] = [
    {      
      isbn: 98765,
      nombre: 'bookName',
      cantidadInventario: 10,
      cantidadDisponible:10    
    },
    {      
      isbn: 98765,
      nombre: 'bookName',
      cantidadInventario: 10,
      cantidadDisponible:10    
    },
    {      
      isbn: 98765,
      nombre: 'bookName',
      cantidadInventario: 10,
      cantidadDisponible:10    
    },
    {      
      isbn: 98765,
      nombre: 'bookName',
      cantidadInventario: 10,
      cantidadDisponible:10    
    }
  ];

  public page:number = 1;
  public pageSize:number = 4;
  public collectionSize:number = this.books.length;

  constructor(private service:RestService) { }

  ngOnInit() {
    //this.getBooks();
  }

  /**
   * Gets the list of books in the inventory 
   *
   */
  private getBooks(){
    let url = `obtenerLibrosDisponibles`;
    this.service.queryExternalApi(url).subscribe(
      response => {
        let result = response.json();
        if (result) {
          this.books = result;
        
        } else {          
          console.log('error');
        }
      },
      err => {
        console.log(err);
      }
    ); 
  }

}
