import { JsonPipe, NgFor } from "@angular/common";
import { Component, inject, OnInit } from "@angular/core";
import { NotificationService } from "@app/service/notification/notification.service";

@Component({
  selector: "app-notification",
  standalone: true,
  imports: [NgFor, JsonPipe],
  templateUrl: "./notification.component.html",
  styleUrls: ["./notification.component.scss"], // corrected to styleUrls
})
export class NotificationComponent implements OnInit {
  private notificationService = inject(NotificationService);
  public notifications: string[] = [];
  private stompClient: any;
  rollNumber = 1209015;

  ngOnInit(): void {
    this.stompClient = this.notificationService.connect(
      "http://localhost:3333/socket"
    );

    this.stompClient.connect({}, (frame: any) => {
      console.log("Connected: " + frame);

      this.stompClient.subscribe(
        "/topic/notification/" + this.rollNumber,
        (notification: any) => {
          if (notification.body) {
            this.notifications.push(JSON.stringify(notification.body));
          }
        }
      );
    });
  }
}
