#!/bin/bash
input="random_10k.csv"
# Set "," as the field separator using $IFS
# and read line by line using while read combo 
while IFS=',' read -r f1 f2
do
  curl -H "Content-Type: application/json" -X POST -d '{"lat":'$f1',"lon":'$f2'}' http://localhost:8080/geotest
done < "$input"
