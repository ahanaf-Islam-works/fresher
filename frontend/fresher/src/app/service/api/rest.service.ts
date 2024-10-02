import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { devENV } from '@app/env/dev.env';
import { Options } from '@app/types/Options';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
/**
 * ApiServiceService is a service that is used to make http requests
 *  @Usage Notes : This service is used to make http requests to the server. use this service to make http requests to the server.
 */
export class RestService {
  constructor() {}
  private httpClient: HttpClient = inject(HttpClient);

  serverUrl = devENV.serverUrl;

  /**
   * @param endPoint only /api/... is required
   * @param options for the request ex. headers, params, etc.
   */

  get<Type>(endPoint: string, options?: Options): Observable<Type> {
    return this.httpClient.get<Type>(
      this.serverUrl + endPoint,
      options || {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    ) as Observable<Type>;
  }

  /**
   * @param endPoint
   * @param options for the request ex. headers, params, etc.
   * @param body for the request
   */

  post<Type>(endPoint: string, body: any, options?: Options): Observable<Type> {
    return this.httpClient.post<Type>(
      this.serverUrl + endPoint,
      JSON.stringify(body),
      options || {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    ) as Observable<Type>;
  }

  /**
   * @param endPoint
   * @param options for the request ex. headers, params, etc.
   * @param body for the request
   */
  put<Type>(endPoint: string, body: any, options?: Options): Observable<Type> {
    return this.httpClient.put<Type>(
      this.serverUrl + endPoint,
      body,
      options || {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    ) as Observable<Type>;
  }

  /**
   * @param endPoint
   * @param options for the request ex. headers, params, etc.
   */
  delete<Type>(endPoint: string, options?: Options): Observable<Type> {
    return this.httpClient.delete<Type>(
      this.serverUrl + endPoint,
      options || {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    ) as Observable<Type>;
  }
}
