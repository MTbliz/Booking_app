#!/bin/bash

echo "Add records to database:"
curl --data "" http://localhost:8080/configuration/
echo 
printf "\n"
echo "Get list of screenings [date: 2020-04-25,timeFrom: 09:00, timeTo 22:00]:"
curl 'http://localhost:8080/screenings?screeningDateTime=%7B%22date%22:%222020-04-25%22,%22timeFrom%22:%2209:00%22,%22timeTo%22:%2222:00%22%7D'
echo 
printf "\n"
echo "Get list of available seats from choosen screening [screening id=1]:"
curl 'http://localhost:8080/availableseats?screeningDateTime=%7B%22date%22:%222020-02-25%22,%22timeFrom%22:%2209:00%22,%22timeTo%22:%2222:00%22%7D&id=1'
echo 
printf "\n"
echo "Send Reservation Data with mistake in firstname:"
curl -d '{"user":{"firstName":"Ja","lastName":"Kowalski"},"listOfSeats":[3,4],"adult":0,"student":1,"child":1}' -H "Content-Type: application/json" -X POST http://localhost:8080/cinemasummary/
echo 
printf "\n"
echo "Send Reservation Data with mistake in lastname:"
curl -d '{"user":{"firstName":"Jan","lastName":"Kowa_lski"},"listOfSeats":[3,4],"adult":0,"student":1,"child":1}' -H "Content-Type: application/json" -X POST http://localhost:8080/cinemasummary/
echo 
printf "\n"
echo "Get list of screenings [date: 2020-02-25,timeFrom: 09:00, timeTo 22:00]:"
curl 'http://localhost:8080/screenings?screeningDateTime=%7B%22date%22:%222020-02-25%22,%22timeFrom%22:%2209:00%22,%22timeTo%22:%2222:00%22%7D'
echo 
printf "\n"
echo "Try to make reservation for movie later then 15min before the screening begins:"
curl -d '{"user":{"firstName":"Janek","lastName":"Kowalski"},"listOfSeats":[79,80],"adult":0,"student":1,"child":1}' -H "Content-Type: application/json" -X POST http://localhost:8080/cinemasummary/
echo 
printf "\n"
echo "Try to make reservation with empty seat between two reserved seats:"
curl -d '{"user":{"firstName":"Janek","lastName":"Kowalski"},"listOfSeats":[1,3],"adult":0,"student":1,"child":1}' -H "Content-Type: application/json" -X POST http://localhost:8080/cinemasummary/
echo 
printf "\n"
echo "Try to make reservation with correct data:"
curl -d '{"user":{"firstName":"Janek","lastName":"Kowalski"},"listOfSeats":[1,2],"adult":0,"student":1,"child":1}' -H "Content-Type: application/json" -X POST http://localhost:8080/cinemasummary/
echo 
printf "\n"
echo "Try to make reservation for alredy reserved seats:"
curl -d '{"user":{"firstName":"Janek","lastName":"Kowalski"},"listOfSeats":[1,2],"adult":0,"student":1,"child":1}' -H "Content-Type: application/json" -X POST http://localhost:8080/cinemasummary/
echo 
printf "\n"

pkill -f "java.*booking_app-0.0.1-SNAPSHOT"





