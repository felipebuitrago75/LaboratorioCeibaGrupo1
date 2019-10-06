import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  public language:string = "";
  constructor(public translate:TranslateService) { }

  ngOnInit() {
    this.language = this.translate.getDefaultLang();
  }

  /**
   * Changes the language of the system
   * 
   * 
   */
  changeLanguage(){
    this.translate.setDefaultLang(this.language);    
    this.translate.use(this.language);
  }

}
