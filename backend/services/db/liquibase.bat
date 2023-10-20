@REM This script uses Liquibase to manage database migrations. The script executes Liquibase commands inside a Docker container.
@REM
@REM List of liquibase available commands:
@REM https://docs.liquibase.com/commands/home.html
@REM
@REM The available parameters are:
@REM
@REM     - COMMAND: the Liquibase command to be executed.
@REM     - USERNAME: Parameter to define the database user credentials.
@REM     - PASSWORD: Parameter to define the database user password credentials.
@REM     - NETWORK_NAME: Parameter to define the name of the Docker network that the container should be attached to.
@REM     - DB_URL: Parameter to define the URL for the database connection.
@REM     - DB_DRIVER: Parameter to define the driver for the database connection. Example: org.postgresql.Driver
@REM
@REM If the user fails to provide the COMMAND parameter when running the script, an error message is displayed, indicating the correct usage of the script.
@REM
@REM Otherwise, the script runs the Liquibase container in the network defined by the %NETWORK_NAME% parameter, mounting the changelog directory as the /liquibase/changelog/ directory in the container.
@REM
@REM With this script, you can easily manage your database migrations using Liquibase.

@echo off
SET LIQUIBASE_VERSION=4.19

SET COMMAND=%1
SET USERNAME=%2
SET PASSWORD=%3
SET NETWORK_NAME=%4
SET DB_URL=%5
SET DB_DRIVER=%6

IF "%COMMAND%"=="" (
  ECHO "Error: missing argument. Usage: %~f0 [command] [username] [password] [network_name] [db_url]"
) ELSE IF "%USERNAME%"=="" (
  ECHO "Error: missing parameter. Please provide the username for the database connection."
) ELSE IF "%PASSWORD%"=="" (
  ECHO "Error: missing parameter. Please provide the password for the database connection."
) ELSE IF "%NETWORK_NAME%"=="" (
  ECHO "Error: missing parameter. Please provide the network name for the Docker container."
) ELSE IF "%DB_URL%"=="" (
  ECHO "Error: missing parameter. Please provide the database URL."
) ELSE IF "%DB_DRIVER%"=="" (
  ECHO "Error: missing parameter. Please provide the driver for the database connection. Example: org.postgresql.Driver"
) ELSE (
    docker run ^
    --network %NETWORK_NAME% ^
    --rm ^
    -v %CD%/changelog:/liquibase/changelog/ ^
    liquibase/liquibase:%LIQUIBASE_VERSION% %COMMAND% ^
        --changelog-file=db.changelog.xml ^
        --url=%DB_URL% ^
        --username=%USERNAME% ^
        --password=%PASSWORD% ^
        --driver=%DB_DRIVER%
)