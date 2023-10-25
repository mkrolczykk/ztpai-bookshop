# Database migration Tool

### This service uses liquibase tool as a db migration service

Use ```liquibase.bat``` (Windows) or ```liquibase.sh``` (Linux, MacOS) 
for managing, applying and reverting database schema changes.

#### More information about usage can be found inside scripts.

### Example usage

Windows
```
$ .\liquibase.bat update postgres postgres book_shop jdbc:postgresql://bookshop_db:5432/bookshop_db org.postgresql.Driver
```

Linux, MacOS
```
$ ./liquibase.sh update postgres postgres book_shop jdbc:postgresql://bookshop_db:5432/bookshop_db org.postgresql.Driver
```
