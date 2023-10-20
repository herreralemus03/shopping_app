import { Injectable } from '@angular/core';
import { BaseHttpService } from '../base-http.service';
import { Page } from 'src/app/interfaces/page.interface';
import { ResponseEntity } from 'src/app/interfaces/response-entity.interface';
import { Observable } from 'rxjs';
import { Country } from 'src/app/interfaces/country.interface';

@Injectable({
  providedIn: 'root'
})
export class CountriesService {

  constructor(private httpService : BaseHttpService) { }

  public loadCountries(page : number, size : number) : Observable<ResponseEntity<Page<Country>>> {
    return this.httpService.get('/countries/get/paged', {page : page, size : size})
  }
}
