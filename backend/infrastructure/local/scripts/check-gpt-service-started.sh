#!/bin/bash
# check-gpt-service-started.sh

# TODO -> update script to check gpt service health endpoint
#apt-get update -y
#
#yes | apt-get install curl
#
#curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://gpt-service-1:8490/health)
#
#echo "result status code:" "$curlResult"
#
#while [[ ! $curlResult == "200" ]]; do
#  >&2 echo "GPT service server is not up yet!"
#  sleep 2
#  curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://gpt-service-1:8490/health)
#done