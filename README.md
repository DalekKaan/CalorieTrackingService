# Calorie tracking service
This is testing task for 1221Systems job interview.

*Note:* [Amplicode](https://amplicode.ru/) is used just for CRUD controller generation

# Build & run

To prepare the environment you can use docker compose setup:

```bash
# create extra folders to store database data
mkdir -p docker/pgadmin docker/postgres
docker composer up -d
```

Access:
- Database connection: `postgres:changeme@localhost:5432/postgres`
- PgAdmin: http://localhost:5050

*Note:* Use `pgadmin_container` as a database host when configure PgAdmin new connection.