:: START WEB APPLICATION
start "" "http://localhost:1052"

:: BUILD AND START FRONTEND
cd frontend && call npm install && cd ..
start cmd.exe /k "cd frontend && ng serve"

:: CREATE DATABASE (CHANGE PORT AND PASSWORD)
set refresher_port=5210
set refresher_password=password2501

set refresher_username=postgres
set PGPASSWORD=%refresher_password%
psql -U postgres -p %refresher_port% -d %refresher_username% -c "CREATE DATABASE refresher;"

:: BUILD AND START BACKEND
cd backend && mvnw.cmd spring-boot:run
