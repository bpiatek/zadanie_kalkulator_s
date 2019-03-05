import {Component, OnInit, Input, Output} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {appResponse} from "../appResponse";
import {HttpHeaders, HttpClient} from "@angular/common/http";

const endpoint = 'http://localhost:8080/api/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Component({
  selector: 'app-request-send',
  templateUrl: './request-send.component.html',
  styleUrls: ['./request-send.component.css']
})
export class RequestSendComponent implements OnInit {

  @Input() data = {dailyWage:'', country:''};
  @Output() localVar:appResponse = null;

  constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient) { }

  ngOnInit() {
  }

  postExchangeForm(data) {
    return this.http.post<appResponse>(endpoint + 'exchange', data, httpOptions)
      .subscribe(
        (val) => {
          this.localVar = val;
        }
      )
  }
}
