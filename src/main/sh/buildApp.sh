#!bin/bash
cd /home/$USERNAME/bookingapplication/Booking_app
mvn clean install 
while ! test -f "/home/$USERNAME/bookingapplication/Booking_app/target/booking_app-0.0.1-SNAPSHOT.jar"; do
sleep 5
echo "Still waiting"
done
cd /home/$USERNAME/bookingapplication/Booking_app/target
java -jar booking_app-0.0.1-SNAPSHOT.jar &


