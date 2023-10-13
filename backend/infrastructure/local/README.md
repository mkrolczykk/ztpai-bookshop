## Notes

This folder is intended for development and testing purposes only. It should not be used in a production environment without appropriate security and modifications.

## How to START local env

1. Launch docker. </br >

2. Go to 'infrastructure/local' (the same directory where this README.md file is placed) in terminal and type
```
$ docker-compose up
```
Docker containers in terminal BACKGROUND option:
```
$ docker-compose up -d
```

All containers local metrics/data e.t.c will be written under 'infrastructure/local/containers-data' folder.

## How to STOP local env

1. To stop everything, type:
```
$ docker-compose down
```

2. [OPTIONAL] Remove 'containers-data' folder from 'infrastructure/local' directory.

If step 2 proceeded, after new start docker will create new resources (database e.t.c), if not, will use existing ones.

## How to START ONLY PostgresDB (for testing)

```
$ docker-compose -f common.yml -f postgres_database.yml up
```