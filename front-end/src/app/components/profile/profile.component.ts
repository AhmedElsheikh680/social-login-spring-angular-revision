import { Component, OnInit } from '@angular/core';
import {SocialAuthService, SocialUser} from "angularx-social-login";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  isLogin: boolean | undefined;
  socialUser: SocialUser = new SocialUser;
  constructor(private socialAuthService: SocialAuthService) { }

  ngOnInit(): void {

    this.socialAuthService.authState.subscribe(
      data => {
        this.isLogin =(data !=null);
        this.socialUser = data;
      }
    )
  }

}
