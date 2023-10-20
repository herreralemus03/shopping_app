import { Country } from "./country.interface";
import { Shipping } from "./shipping.interface";
import { Store } from "./store.interface";

export interface Order {
    id : string;
    country : Country;
    store : Store;
    shipping : Shipping[];
}