#!/bin/bash
# check-bookservice-server-started.sh


# TODO -> update script to check book service health endpoint
#apt-get update -y
#
#yes | apt-get install curl
#
#curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://book_service_1:8290/health)
#
#echo "result status code:" "$curlResult"
#
#while [[ ! $curlResult == "200" ]]; do
#  >&2 echo "Bookshop service server is not up yet!"
#  sleep 2
#  curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://book_service_1:8290/health)
#done