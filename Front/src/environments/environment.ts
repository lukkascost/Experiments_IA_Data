// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

/* export const environment = {
    production: false,
    apiUrlBack: 'http://205.237.201.197:82',
    tokenWhitelistedDomains: '205.237.201.197:82',
    tokenBlacklistedDomains: '205.237.201.197:82/users/login'
}; */

export const environment = {
  production: false,
  apiUrlBack: 'http://54.90.38.50',
  tokenWhitelistedDomains: 'localhost:5000',
  tokenBlacklistedDomains: 'localhost:5000/users/login',
  websocketExpiration: 1572986429
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
