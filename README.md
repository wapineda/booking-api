# Read Me First
# Booking API
## Running the code in local mode
- Run ´mvn spring-boot:run´ from a console in the directory of the project

## Some considerations
1. As the asked quality of service is very high, several resources should be dedicated to host this API
2. A load balancer should be used, in order to redirect the network traffic to the available resources
3. The API should be deployed to different availability zones, in case one Data Center goes down (disaster recovery)
4. An H2 in-memory database was used for the purpose of this test, but it is not recommended in a real production environment

##Endpoints available
1. GET "/api/book/all" --> Retrieves all the bookings stored in the database
2. GET "/api/book/users" --> Retrieves all the users stored in the database
3. GET "/api/book/checkAvailability" --> Shows the room availability
4. GET "/api/book/current" --> Retrieves the current bookings
5. POST "/api/book/" --> Creates a new booking
6. PUT "/api/book/" --> Updates an existing booking
7. PUT "/api/book/cancel" --> cancels an existing booking
8. 