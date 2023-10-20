import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Article } from 'src/app/interfaces/article.interface';
import { Client } from 'src/app/interfaces/client.interface';
import { Country } from 'src/app/interfaces/country.interface';
import { Order } from 'src/app/interfaces/order.interface';
import { Page } from 'src/app/interfaces/page.interface';
import { Product } from 'src/app/interfaces/product.interface';
import { ResponseEntity } from 'src/app/interfaces/response-entity.interface';
import { Store } from 'src/app/interfaces/store.interface';
import { ClientsService } from 'src/app/services/clients/clients.service';
import { CountriesService } from 'src/app/services/countries/countries.service';
import { OrdersService } from 'src/app/services/orders/orders.service';
import { ShoppingCartService } from 'src/app/services/shopping_cart/shopping-cart.service';
import { StoresService } from 'src/app/services/stores/stores.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  currentProducts : Product[] = [];

  clients : Client[] = [];
  countries : Country[] = [];
  stores : Store[] = [];

  orderForm = this.formBuilder.group({
    countryId : ['', Validators.required],
    storeId : ['', Validators.required],
    clientId : ['', Validators.required],
    notes: ['', ],
  });

  constructor(
    private shoppingCartService : ShoppingCartService, 
    public dialogRef: MatDialogRef<ShoppingCartComponent>,
    private ordersService : OrdersService,  
    private storesService : StoresService,
    private clientsService : ClientsService,
    private countriesService : CountriesService,
    private formBuilder : FormBuilder,
  ){}
  
  ngOnInit(): void {
    this.storesService.loadStores(0, 100).subscribe({
      next: (resp : ResponseEntity<Page<Store>>) => {
        const { content } = resp.body;
        this.stores = content;
      }
    });
    this.clientsService.loadClients(0, 100).subscribe({
      next: (resp : ResponseEntity<Page<Client>>) => {
        const { content } = resp.body;
        this.clients = content;
      }
    });
    this.countriesService.loadCountries(0, 100).subscribe({
      next: (resp : ResponseEntity<Page<Country>>) => {
        const { content } = resp.body;
        this.countries = content;
      }
    });
    this.currentProducts = this.shoppingCartService.getCurrentProducts();
  }

  closeDialog() {
    this.dialogRef.close();
  }

  buyItems(){
    const countryId = this.orderForm.get('countryId')?.value;
    const storeId = this.orderForm.get('storeId')?.value;
    const clientId = this.orderForm.get('clientId')?.value;
    const notes = this.orderForm.get('notes')?.value;
    
    const country = this.countries.find(country => country.id == countryId);
    const store = this.stores.find(store => store.id == storeId);
    const client = this.clients.find(client => client.id == clientId);

    const order : Order = {
      id : "",
      country : country!,
      store : store!,
      shipping : [
        {
          id: "",
          notes : notes ?? "",
          client : client!,
          articles: this.parseProductToArticleShipping(),
        }
      ]

    }
    this.ordersService.createOrder(order).subscribe({
      next: (result) => {
        if(result.code == '200'){
          this.dialogRef.close(true);
        }
      }
    });
  }

  parseProductToArticleShipping() : Article[] {
    return this.currentProducts.map(product => this.parseProductToArticle(product));
  }

  parseProductToArticle(product : Product) : Article {
    return {
      id: "",
      articleNotes: "",
      amount : product.amount,
      product : {
        id: product.id,
        productName: product.productName,
        productDescription: product.productDescription,
        weight: product.weight,
        weightUnit: product.weightUnit,
        price: product.price,
        amount: product.amount,
        index: product.index
      }
    }
  }
  
}
