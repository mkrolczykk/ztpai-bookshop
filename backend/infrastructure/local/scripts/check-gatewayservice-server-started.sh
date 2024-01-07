#!/bin/bash
# check-gatewayservice-server-started.sh

# TODO -> update script to check gateway service health endpoint
#apt-get update -y
#
#yes | apt-get install curl
#
#curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://gateway-service-1:8081/health)
#
#echo "result status code:" "$curlResult"
#
#while [[ ! $curlResult == "200" ]]; do
#  >&2 echo "Gateway service server is not up yet!"
#  sleep 2
#  curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://gateway-service-1:8081/health)
#done