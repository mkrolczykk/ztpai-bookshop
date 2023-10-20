#!/bin/bash
# This script uses Liquibase to manage database migrations. The script executes Liquibase commands inside a Docker container.
#
# List of liquibase available commands:
# https://docs.liquibase.com/commands/home.html
#
# The available parameters are:
#
#    - COMMAND: the Liquibase command to be executed. This is the only required parameter, and should be provided as an argument in the command line.
#    - USERNAME: Parameter to define the database user credentials.
#    - PASSWORD: Parameter to define the database user password credentials.
#    - NETWORK_NAME: Parameter to define the name of the Docker network that the container should be attached to.
#    - DB_URL: Parameter to define the URL for the database connection.
#    - DB_DRIVER: Parameter to define the driver for the database connection. Example: org.postgresql.Driver
#
# If the user fails to provide the COMMAND parameter when running the script, an error message is displayed, indicating the correct usage of the script.
#
# Otherwise, the script runs the Liquibase container in the network defined by the %NETWORK_NAME% parameter, mounting the changelog directory as the /liquibase/changelog/ directory in the container.
#
# With this script, you can easily manage your database migrations using Liquibase.
LIQUIBASE_VERSION=4.19

COMMAND=$1
USERNAME=$2
PASSWORD=$3
NETWORK_NAME=$4
DB_URL=$5
DB_DRIVER=$6

if [ -z "$1" ] || [ -z "$2" ] || [ -z "$3" ] || [ -z "$4" ] || [ -z "$5" ] || [ -z "$6" ]; then
  echo echo "Error: missing argument. Usage: $0 [command] [username] [password] [network_name] [db_url] [db_driver]"
else
docker run \
  --network "$NETWORK_NAME" \
  --rm \
  -v "$PWD"/changelog:/liquibase/changelog/ \
liquibase/liquibase:$LIQUIBASE_VERSION "$COMMAND" \
  --changelog-file=db.changelog.xml \
  --url="$DB_URL" \
  --username="$USERNAME" \
  --password="$PASSWORD" \
  --driver="$DB_DRIVER"
fi