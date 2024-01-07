#!/bin/bash
# check-notifications-service-started.sh

# TODO -> update script to check notifications service health endpoint
#apt-get update -y
#
#yes | apt-get install curl
#
#curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://notifications-service-1:8390/health)
#
#echo "result status code:" "$curlResult"
#
#while [[ ! $curlResult == "200" ]]; do
#  >&2 echo "Notifications service server is not up yet!"
#  sleep 2
#  curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://notifications-service-1:8390/health)
#done