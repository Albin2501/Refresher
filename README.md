# REFRESHER #
Project to refresh some programming. Started on 31st March 2025.

Written in VSC and only tested on Windows 11.

Frontend via Angular (Typescript).
Backend via Spring Boot (Java).

To install all necessities ([Java](https://www.java.com/), [Maven](https://maven.apache.org/), [npm](https://www.npmjs.com/), [Angular](https://angular.dev/)) use [Chocolatey](https://chocolatey.org/install) if required:
```
choco install openjdk
choco install maven
choco install nodejs
npm install -g @angular/cli
```

Execute [RUN.cmd](RUN.cmd) to build and start the application. Giving permission might be required.

## Grid ##

A grid may be selected and colored in. Selecting a singular cell or an area of cells will change the appearance to a randomized color. If cells of different colors are adjacent to each other, then they will acquire a different uniform color. See *flooding algorithm* for more information.
