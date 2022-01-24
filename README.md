Перед запуском необходимо запустить PostgreSQL:
docker run --name account-postgres -p 5433:5432 -e POSTGRES_USER=account -e POSTGRES_PASSWORD=account -e POSTGRES_DB=account -d postgres:14

Добавить oauth2 авторизацию и интеграцию между gateway и stock-service.