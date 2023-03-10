import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MenuCategory } from 'src/app/models/category';

@Component({
  selector: 'app-navbar-megamenus',
  templateUrl: './navbar-megamenus.component.html',
  styleUrls: ['./navbar-megamenus.component.css']
})
export class NavbarMegamenusComponent implements OnInit {

  constructor() { }

  @Output()
  OnSubmit: EventEmitter<void> = new EventEmitter<void>()

  @Input()
  headerMenuCategoriesParam: MenuCategory[] = []

  ngOnInit(): void {
  }

  hideNavbarLinks() {
    this.OnSubmit.emit()
  }

}
