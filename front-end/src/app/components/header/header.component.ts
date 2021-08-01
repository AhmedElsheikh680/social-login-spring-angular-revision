import { Component, OnInit } from '@angular/core';
import {SocialAuthService} from "angularx-social-login";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLogin: boolean | undefined;
  constructor(private socialAuthService: SocialAuthService) { }

  ngOnInit(): void {
    this.socialAuthService.authState.subscribe(
      data => {
        this.isLogin = (data != null)
      }
    )
  }

}
