import { Component, EventEmitter, Output } from '@angular/core';
import { UserService } from 'src/app/services/user.service';


interface SideNavToggle {
  screenWidth: number;
  collapsed: boolean;
}


@Component({
  selector: 'app-candidate-sidenav',
  templateUrl: './candidate-sidenav.component.html',
  styleUrls: ['./candidate-sidenav.component.scss']
})
export class CandidateSidenavComponent {
  @Output() onToggleSideNav: EventEmitter<SideNavToggle> = new EventEmitter();
  collapsed = false;
  screenWidth = 0;
  
  email = this.userService.showProfile();

  constructor(private userService: UserService) { }

  dataArr = [{
    routeLink: 'candidate/homepage',
    icon: "fal fa-home",
    label: "Dashboard"
  },
  {
    routeLink: "candidate/findByEmail",
    queryParams: { email: this.email},
    queryParamsHandling: 'merge',
    icon: "fa fa-user",
    label: "Profile"
  }
  ];

  toggleCollapse(): void {

    this.collapsed = !this.collapsed;
    this.onToggleSideNav.emit({ collapsed: this.collapsed, screenWidth: this.screenWidth });
  }

  closeSidenav(): void {
    this.collapsed = false
    this.onToggleSideNav.emit({ collapsed: this.collapsed, screenWidth: this.screenWidth });
  }

}
