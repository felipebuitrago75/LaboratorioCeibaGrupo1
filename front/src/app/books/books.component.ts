import { Component, OnInit } from '@angular/core';
import { RestService } from '../services/rest.service';
import { Book } from '../interfaces/book';
import swal from "sweetalert2";
import { TranslateService } from '@ngx-translate/core';



@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss']
})
export class BooksComponent implements OnInit {

  public books: Book[] = [];

  public page:number = 1;
  public pageSize:number = 4;
  public collectionSize:number = this.books.length;

  constructor(private service:RestService, public translate: TranslateService) { }

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


   /**
   * Ask for confirmation before to delete the book
   * @param string bookIsbn isbn book to delete
   */
  confirmDelete(bookIsbn) {
    swal({
      title: this.translate.instant("alerts.confirm"),
      text: this.translate.instant("alerts.sure_to_delete"),
      type: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: this.translate.instant("buttons.yes"),
      cancelButtonText: this.translate.instant("buttons.cancel")
    }).then(result => {
      if (result.value) {
        this.deleteBook(bookIsbn);
      }
    });
  }

  /**
   * Deletes the book that corresponds to the given isbn
   * 
   * @param bookIsbn isbn book to delete
   */
  private deleteBook(bookIsbn){
    let url = `eliminarLibro/${bookIsbn}`;
    this.service.queryDeleteRegular(url).subscribe(
      response => {
        let result = response.json();
        if (result) {          
          //this.router.navigate(["/books"]);        
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
