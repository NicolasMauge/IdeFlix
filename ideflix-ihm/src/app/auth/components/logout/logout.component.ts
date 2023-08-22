import {Component, OnInit} from '@angular/core';
import {MessageService} from "../../../core/services/common/message.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private messageSvc: MessageService,
              private route: Router) {
  }

  ngOnInit(): void {

    localStorage.clear();

    this.messageSvc.show('Vous avez bien été déconnecté.', 'success');

    this.route.navigate(['']);

  }

}
