import { Injectable } from '@angular/core';
import { BaseHttpService } from '../base-http.service';
import { Observable } from 'rxjs';
import { ResponseEntity } from 'src/app/interfaces/response-entity.interface';
import { Page } from 'src/app/interfaces/page.interface';
import { Order } from 'src/app/interfaces/order.interface';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor( private httpService : BaseHttpService ) { }

  public loadOrders(page: number, size: number) : Observable<ResponseEntity<Page<Order>>> {
    return this.httpService.get('/orders/get/paged', { page : page, size : size });
  }

  public createOrder(order : Order) : Observable<ResponseEntity<Order>> {
    return this.httpService.post('/orders/create', order);
  }

}
