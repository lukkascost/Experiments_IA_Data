import {Component, HostListener, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {IMenuItem, NavigationService} from '../../../../services/navigation.service';
import {NavigationEnd, Router} from '@angular/router';
import {filter} from 'rxjs/operators';
import {Utils} from '../../../../utils';

@Component({
    selector: 'app-sidebar-large',
    templateUrl: './sidebar-large.component.html',
    styleUrls: ['./sidebar-large.component.scss']
})
export class SidebarLargeComponent implements OnInit, OnChanges {

    selectedItem: IMenuItem;

    nav: IMenuItem[];

    @Input() closeChild = [];

    constructor(
        public router: Router,
        public navService: NavigationService
    ) {
    }

    ngOnChanges(changes: SimpleChanges) {

        if (this.closeChild) {
            this.closeChildNav();
        }

    }

    ngOnInit() {
        this.updateSidebar();
        // CLOSE SIDENAV ON ROUTE CHANGE
        this.router.events.pipe(filter(event => event instanceof NavigationEnd))
            .subscribe((routeChange) => {
                this.closeChildNav();
                if (Utils.isMobile()) {
                    this.navService.sidebarState.sidenavOpen = false;
                }
            });

        this.navService.menuItems$
            .subscribe((items) => {
                this.nav = items;
                this.setActiveFlag();
            });
    }

    selectItem(item) {
        this.navService.sidebarState.childnavOpen = true;
        this.selectedItem = item;
        this.setActiveMainItem(item);
    }

    closeChildNav() {
        this.navService.sidebarState.childnavOpen = false;
        this.setActiveFlag();
    }

    onClickChangeActiveFlag(item) {
        this.setActiveMainItem(item);
    }

    setActiveMainItem(item) {
        this.nav.forEach(itemFor => {
            itemFor.active = false;
        });
        item.active = true;
    }

    setActiveFlag() {
        if (window && window.location) {
            const activeRoute = window.location.hash || window.location.pathname;
            if (this.nav) {
                this.nav.forEach(item => {
                    item.active = false;
                    if (activeRoute.indexOf(item.state) !== -1) {
                        this.selectedItem = item;
                        item.active = true;
                    }
                    if (item.sub) {
                        item.sub.forEach(subItem => {
                            subItem.active = false;
                            if (activeRoute.indexOf(subItem.state) !== -1) {
                                this.selectedItem = item;
                                item.active = true;
                            }
                            if (subItem.sub) {
                                subItem.sub.forEach(subChildItem => {
                                    if (activeRoute.indexOf(subChildItem.state) !== -1) {
                                        this.selectedItem = item;
                                        item.active = true;
                                        subItem.active = true;
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
    }

    updateSidebar() {
        if (Utils.isMobile()) {
            this.navService.sidebarState.sidenavOpen = false;
            this.navService.sidebarState.childnavOpen = false;
        } else {
            this.navService.sidebarState.sidenavOpen = true;
        }
    }

    verifyUrl(item) {
        if (item && item.icon) {
            return item.icon.includes('/');
        }
    }

    @HostListener('window:resize', ['$event'])
    onResize(event) {
        this.updateSidebar();
    }

}
