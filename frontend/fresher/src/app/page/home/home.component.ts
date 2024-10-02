import { Component } from "@angular/core";
import { NotificationComponent } from "@app/layout/notification/notification.component";

@Component({
  selector: "app-home",
  standalone: true,
  imports: [NotificationComponent],
  templateUrl: "./home.component.html",
  styleUrl: "./home.component.scss",
})
export class HomeComponent {}
