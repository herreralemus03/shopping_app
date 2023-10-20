import { Product } from "./product.interface";

export interface Article {
    id : String;
    articleNotes : String;
    amount : number;
    product : Product;
}