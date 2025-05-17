:: CLEAR DATABASE FOR TESTING PURPOSE
set PGPASSWORD=password2501
psql -U postgres -p 5210 -d postgres -c "DROP DATABASE refresher;"
