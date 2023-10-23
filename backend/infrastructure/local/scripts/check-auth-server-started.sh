#!/bin/bash
# check-auth-server-started.sh


# TODO -> update script to check auth service health endpoint
#apt-get update -y
#
#yes | apt-get install curl
#
#curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://auth_service_1:8190/health)
#
#echo "result status code:" "$curlResult"
#
#while [[ ! $curlResult == "200" ]]; do
#  >&2 echo "Authorization server is not up yet!"
#  sleep 2
#  curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://auth_service_1:8190/health)
#done