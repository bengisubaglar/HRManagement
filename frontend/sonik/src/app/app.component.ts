import { Component, OnInit } from '@angular/core';

import { NavigationStart, Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { UserService } from './services/user.service';




interface SideNavToggle{
  screenWidth: number;
  collapsed: boolean;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  showBody: boolean = true;
  showSideBar: boolean = true;
  title = 'sonik';
  isSideNavCollapsed = false;
  screenWidth = 0;
  sideNavType:string = '';

  constructor(private router: Router, private authService:AuthService, private userService: UserService) {
   
  }
  

  onToggleSideNav(data: SideNavToggle): void{
      this.screenWidth = data.screenWidth;
      this.isSideNavCollapsed = data.collapsed;
  }
  ngOnInit(): void {
    this.authService.initializeAuth();
    this.router.events.forEach((event) => {
      if (event instanceof NavigationStart) {
        if(this.userService.getRole() == 'CANDIDATE'){
          this.sideNavType = 'CANDIDATE';
        }
        if(this.userService.getRole() == 'HUMAN_RESOURCES'){
          this.sideNavType = 'HUMAN_RESOURCES';
        }
        const url = event.url.split('?')[0]; 
        this.showBody = url !== '/auth/candidate/login';
        this.showSideBar = (url !== '/') && (url !== '/video-call');
        
      }
    });
  }
}
