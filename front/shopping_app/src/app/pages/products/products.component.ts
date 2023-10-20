import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { Page } from 'src/app/interfaces/page.interface';
import { Product } from 'src/app/interfaces/product.interface';
import { ResponseEntity } from 'src/app/interfaces/response-entity.interface';
import { ProductsService } from 'src/app/services/products/products.service';
import { MatDialog } from '@angular/material/dialog';
import { ShoppingCartComponent } from '../shopping_cart/shopping-cart/shopping-cart.component';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css'],
})
export class ProductsComponent implements OnInit {
  
  length = 0;
  pageSize = 12;
  pageIndex = 0;
  pageSizeOptions = [5, 10, 25];

  hidePageSize = true;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;

  public currentProducts : Product[] = [];
  public cartProducts : Product[] = [];

  constructor(
    private productsService : ProductsService, 
    private dialog : MatDialog,
  ){}

  ngOnInit(): void {
    this.cartProducts = this.getCurrentProducts().sort((a, b) => a.index - b.index);
    this.getProducts();
  }
  
  openDialog() {
    const dialogRef = this.dialog.open(ShoppingCartComponent, {
      width: '70%',
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result) {
        this.cartProducts = [];
        sessionStorage.setItem("products", "[]");
      }
    });
  }

  handlePageEvent(e: PageEvent) {
    this.pageIndex = e.pageIndex;
    this.getProducts(this.pageIndex);
  }

  getProducts(page : number = 0){
    this.productsService.loadProducts(page, 12).subscribe({
      next: (resp : ResponseEntity<Page<Product>>) => {
          const { content, totalElements } = resp.body;
          this.currentProducts = content;
          this.length = totalElements;
          this.syncProductAmounts();
      },
      error: (error) => {
      }
    });
  }

  syncProductAmounts(){
    const newList = this.currentProducts.filter( product => {
      const currentProduct = this.cartProducts.find(cartProduct => cartProduct.id === product.id);
      if(currentProduct != null) product.amount = currentProduct.amount;
      else product.amount = 0;  
      return true;
    });
    this.currentProducts = newList;
  }

  addProduct(product : Product){
    this.cartProducts = this.addProductToCart(product).sort((a, b) => a.index - b.index);
  }

  removeProduct(product : Product){
    this.cartProducts = this.removeCartProduct(product).sort((a, b) => a.index - b.index);
  }

  public addProductToCart(product : Product) : Product[] {
    let currentProducts = this.getCurrentProducts();
    const existingProduct = currentProducts.filter(current => current.id == product.id)
                                            .at(0);
    if(existingProduct != null){
      currentProducts = currentProducts.filter(product => product.id != existingProduct.id);
      existingProduct.amount = existingProduct.amount + 1;
      currentProducts = [...currentProducts, existingProduct];
      const newProductsParsed = JSON.stringify(currentProducts);
      sessionStorage.setItem("products", newProductsParsed);
      return currentProducts;
    } else {
      product.amount = 1;
      product.index = currentProducts.length + 1;
      currentProducts = [...currentProducts, product];
      const newProductsParsed = JSON.stringify(currentProducts);
      sessionStorage.setItem("products", newProductsParsed);
      return currentProducts;
    }
  }

  public removeCartProduct(product : Product) : Product[] {
    let currentProducts = this.getCurrentProducts();
    const existingProduct = currentProducts.filter(current => current.id == product.id)
                                            .at(0)!;
    currentProducts = currentProducts.filter(product => product.id != existingProduct.id);
    existingProduct.amount = existingProduct.amount - 1;
    if(existingProduct.amount < 1) currentProducts = currentProducts.filter(product => product.id != existingProduct.id);
    else currentProducts = [...currentProducts, existingProduct];
    const newProductsParsed = JSON.stringify(currentProducts);
    sessionStorage.setItem("products", newProductsParsed);
    return currentProducts;
  }

  public getCurrentProducts() : Product[] {
    const currentProductsStr = sessionStorage.getItem("products") != null ? sessionStorage.getItem("products") : "[]"; 
    const currentProducts = JSON.parse(currentProductsStr!);
    return currentProducts;
  }
}
