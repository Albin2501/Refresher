# REFRESHER #
Project to refresh some programming. Started on 31st March 2025.

Written in VSC and only tested on Windows 11.

Frontend via Angular (Typescript).
Backend via Spring Boot (Java).

## Setup ##

To install all necessities ([Java](https://www.java.com/), [Maven](https://maven.apache.org/), [npm](https://www.npmjs.com/), [Angular](https://angular.dev/)) use [Chocolatey](https://chocolatey.org/install) if required.:
```
choco install openjdk
choco install maven
choco install nodejs
npm install -g @angular/cli
```
[PostgreSQL](https://www.postgresql.org/) is also required and it is recommended to install it through the official website. Adding the bin path of the installed PostgreSQL version as an environment variable in PATH might be required (so that ```psql``` is recognized by Windows). **Important** - when setting up PostgreSQL, replace the port and passwort in [RUN.cmd](RUN.cmd) with your chosen port (may not be 2501 or 1052) and password. It should look like this:
```
set refresher_port=<your_chosen_port>
set refresher_password=<your_chosen_password>
```
**Do not share sensitive data.** The size of this application is about 250MB. All installed programs add up to approximately 1GB.

## Start application ##

Execute [RUN.cmd](RUN.cmd) to build and start the application. Giving permission might be required.

## Grid ##

A grid may be selected and colored in. Selecting a singular cell or an area of cells will change the appearance to a randomized color. If cells of different colors are adjacent to each other, they then will acquire a different uniform color. See *flooding algorithm* for more information.

![showcase](https://github.com/user-attachments/assets/23d06e84-bf91-4c00-8e9a-f643dc1cce49)

## Text Art ##

Any given picture can be converted to ASCII art with a customizable resolution. Previous uploads can be revisited in a history log and copied to the clipboard to be shared anywhere. The original image is gray scaled and gamma corrected and then projected to a hand-selected scale of ASCII characters for the best results.

![preview1](https://github.com/user-attachments/assets/682d9acd-f8f0-44ef-ba33-5de6b1b69e47)

![preview2](https://github.com/user-attachments/assets/a4a6c051-c2fa-46d9-92d2-409c4990ebd7)
