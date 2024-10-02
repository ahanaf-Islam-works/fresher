import { Injectable } from "@angular/core";
import SockJS from "sockjs-client"; // Use default import for sockjs-client
import * as StompJs from "stompjs";

@Injectable({
  providedIn: "root",
})
export class NotificationService {
  public connect(customUrl: string) {
    let socket = new SockJS(customUrl);
    let stompClient = StompJs.over(socket);
    return stompClient;
  }
}
