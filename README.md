> **_Для ревью:_** Я добавил комментарии на русском, которые объясняют некоторые мои решения.

# Calorie tracking service
This is testing task for 1221Systems job interview.

*Note:* [Amplicode](https://amplicode.ru/) is used just for CRUD controller generation

# Build & run

Use `docker-compose` to build & run application containers 

```bash
mkdir -p docker/pgadmin docker/postgres # create extra folders to store database data
docker composer up -d
```

Access:
- Database connection by default: `postgres:changeme@localhost:5432/postgres`
- PgAdmin: http://localhost:5050
- Main application: http://localhost:8080

For HTTP request examples see [Postman collection](1221systems.postman_collection.json).