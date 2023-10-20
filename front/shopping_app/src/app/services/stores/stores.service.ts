import { Injectable } from '@angular/core';
import { BaseHttpService } from '../base-http.service';
import { Store } from 'src/app/interfaces/store.interface';
import { ResponseEntity } from 'src/app/interfaces/response-entity.interface';
import { Observable } from 'rxjs';
import { Page } from 'src/app/interfaces/page.interface';

@Injectable({
  providedIn: 'root'
})
export class StoresService {

  constructor(private httpService : BaseHttpService) { }

  public loadStores(page : number, size : number) : Observable<ResponseEntity<Page<Store>>> {
    return this.httpService.get('/stores/get/paged', {page : page, size : size})
  }

}
