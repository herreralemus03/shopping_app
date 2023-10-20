import { Injectable } from '@angular/core';
import { BaseHttpService } from '../base-http.service';
import { ResponseEntity } from 'src/app/interfaces/response-entity.interface';
import { Page } from 'src/app/interfaces/page.interface';
import { Product } from 'src/app/interfaces/product.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private httpService : BaseHttpService) { }

  public loadProducts (page : number, size : number) : Observable<ResponseEntity<Page<Product>>> {
    return this.httpService.get('/products/get/paged', {page : page, size: size})
  }

  public createProduct (product : Product) : Observable<ResponseEntity<Product>> {
    return this.httpService.post('/products/add', product);
  }

  public updateProduct (product : Product) : Observable<ResponseEntity<Product>> {
    return this.httpService.post('/products/update', product);
  }

  public deleteProduct (id : string) : Observable<ResponseEntity<Product>> {
    return this.httpService.get(`/products/delete/${id}`);
  }
  
}
