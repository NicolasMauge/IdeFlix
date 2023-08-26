import {Component, OnInit} from '@angular/core';
import {MessageService} from "../../../core/services/common/message.service";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private messageSvc: MessageService,
              private route: Router,
              private authSvc: AuthService) {
  }

  ngOnInit(): void {


    this.authSvc.logout()
      .subscribe({
        error: err => {
          this.route.navigate(['/']).then(() => {
            this.messageSvc.show('Erreur lors de la déconnexion', 'error');
          })
        },
        next: response => {
          this.route.navigate(['/']).then(() => {
            this.messageSvc.show('Vous avez bien été déconnecté.', 'success');
          });

        }
      });


  }

}
