:: START WEB APPLICATION
start "" "http://localhost:1052"

:: BUILD AND START FRONTEND
cd frontend && call npm install && cd ..
start cmd.exe /k "cd frontend && ng serve"

:: CREATE DATABASE (CHANGE PORT AND PASSWORD)
set REFRESHER_PORT=5210
set REFRESHER_PASSWORD=password2501
set REFRESHER_USERNAME=postgres
set PGPASSWORD=%REFRESHER_PASSWORD%
psql -U postgres -p %REFRESHER_PORT% -d %REFRESHER_USERNAME% -c "CREATE DATABASE refresher;"

:: BUILD AND START BACKEND
cd backend && mvnw.cmd spring-boot:run
