import { Injectable } from '@angular/core';
import { Product } from 'src/app/interfaces/product.interface';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  constructor() { }

  public addProductToCart(product : Product){
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
      currentProducts = [...currentProducts, product];
      const newProductsParsed = JSON.stringify(currentProducts);
      sessionStorage.setItem("products", newProductsParsed);
      return currentProducts;
    }
  }

  public getCurrentProducts() : Product[] {
    const currentProductsStr = sessionStorage.getItem("products") != null ? sessionStorage.getItem("products") : "[]"; 
    const currentProducts = JSON.parse(currentProductsStr!);
    return currentProducts;
  }
}
