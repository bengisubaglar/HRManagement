import { Component, EventEmitter, Output } from '@angular/core';
import { UserService } from 'src/app/services/user.service';


interface SideNavToggle {
  screenWidth: number;
  collapsed: boolean;
}

@Component({
  selector: 'app-hr-sidenav',
  templateUrl: './hr-sidenav.component.html',
  styleUrls: ['./hr-sidenav.component.scss']
})
export class HrSidenavComponent {

  @Output() onToggleSideNav: EventEmitter<SideNavToggle> = new EventEmitter();
  collapsed = false;
  screenWidth = 0;
  
  email = this.userService.showProfile();

  constructor(private userService: UserService) { }

  dataArr = [{
    routeLink: 'hr/homepage',
    icon: "fal fa-home",
    label: "Dashboard"
  }];

  toggleCollapse(): void {

    this.collapsed = !this.collapsed;
    this.onToggleSideNav.emit({ collapsed: this.collapsed, screenWidth: this.screenWidth });
  }

  closeSidenav(): void {
    this.collapsed = false
    this.onToggleSideNav.emit({ collapsed: this.collapsed, screenWidth: this.screenWidth });
  }

}
