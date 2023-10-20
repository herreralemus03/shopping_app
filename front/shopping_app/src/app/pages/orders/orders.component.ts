import { Component, OnDestroy, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { Order } from 'src/app/interfaces/order.interface';
import { Page } from 'src/app/interfaces/page.interface';
import { ResponseEntity } from 'src/app/interfaces/response-entity.interface';
import { OrdersService } from 'src/app/services/orders/orders.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  
  length = 0;
  pageSize = 12;
  pageIndex = 0;
  pageSizeOptions = [5, 10, 25];

  hidePageSize = true;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;

  displayedColumns: string[] = ['Pais', 'Tienda', 'Cliente', 'Direccion'];
  orders : Order[] = [];
  ordersSubscription : any;

  constructor(
    private ordersService : OrdersService,
  ){}

  ngOnInit(): void {
    this.loadOrders();
  }

  handlePageEvent(e: PageEvent) {
    this.pageIndex = e.pageIndex;
    this.loadOrders(this.pageIndex);
  }

  loadOrders(page : number = 0){
    this.ordersService.loadOrders(page, 12).subscribe({
      next: (resp : ResponseEntity<Page<Order>>) => {
        const { content, totalElements } = resp.body;
        this.orders = content;
        this.length = totalElements;
      }
    });
  }
}
