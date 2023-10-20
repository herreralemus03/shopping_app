import { Component, Input, OnInit } from '@angular/core';
import { BaseHttpService } from '../../services/base-http.service';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styles: [`
  .loader{
    position: fixed;
    left: 0px;
    top: 0px;
    width: 100%;
    height: 100%;
    z-index: 9999;
    background: url('https://mir-s3-cdn-cf.behance.net/project_modules/disp/04de2e31234507.564a1d23645bf.gif') 
                50% 50% no-repeat rgb(249,249,249, 0.4);
    background-size: 10vh;   
  }
`]
})
export class LoadingComponent implements OnInit {

  @Input("disabled") disabled: boolean = true;

  constructor(private httpService : BaseHttpService) { }

  ngOnInit(): void {
    this.httpService.observableLoading.subscribe(value => this.disabled = !value);
  }

}
