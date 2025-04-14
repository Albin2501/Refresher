start /wait /c "cd frontend && npm install && exit"
start cmd.exe /k "cd frontend && ng serve"
start cmd.exe /k "cd backend && mvnw.cmd spring-boot:run"
explorer "http://localhost:1052"
