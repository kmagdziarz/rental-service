# Car rental service

## The problem
We are going to implement a car rental service.
The service will have a list of cars and will be able to rent them to customers.
The service will also be able to return the cars and to list the cars that are currently rented.
The service will be able to list all the cars and to list the cars that are available for rent.
The service will be able to list the cars that are rented by a specific customer.

## Technical details
It's a maven based project, so it should be easy to import it in your favourite IDE. There are some Junit 5 test cases. 
They can be executed from the IDE or from the command line by running:

    mvn test

We use LTS Java version 17 and Java 17 is the baseline here. Feel free to use any Java version (17 or above) which works for you. 
You can change the java version in the pom.xml file by editing following line:

    <java.version>17</java.version>


## Tasks
There are 3 tasks. They depend on each other so start with [Task 1](#task-1) then continue with [Task 2](#task-2).

### Task 1
In this task you are asked to implement 6 methods in the `com.acme.rental.CarRentalServiceImpl` class.
- `rentCar` - This method should rent a car to a customer. The method should return the car that was rented. 
If the car is already rented or does not exist, the method should return null. 
This method should create new client if client does not exist in internal storage.
- `returnCar` - This method should return a car that was rented by a client. The method should return the car that was returned.
If the car is already returned or does not exist, the method should return null.
- `getAvailableCars` - This method should return a list of all available cars.
- `getAllRentedCarsByClient` - This method should return a list of all rented cars by a given client. 
If client does not exist it should return 0.
- `isCarRented` - This method should return true if the car is rented, false otherwise (even if car does not exist).
- `getAllClients` - This method should return a list of all clients.

The assumption is that clients and cars are identified by their ids and each is unique.
The initial list of cars should be injected by the constructor.
Client list (or any other approach for storing clients in memory) should be built during service runtime. 

The `com.acme.rental.Car` represents a car and has the following properties:
- `carId` - a unique identifier of the car.
- `carStatus` - a status of the car (`rented` or `available`). The default status is `available`.

The `com.acme.rental.Client` represents a client and has the following properties:
- `clientId` - a unique identifier of the client.
- `rentedCarIds` - a list of car ids rented by the client.

To focus on the code a test case for this scenario is provided in the `com.acme.rental.CarRentalTask1Test` class.

As a result of this task the test should pass successfully. Feel free to add any additional tests you like.
If your IDE doesn't allow you to run the test directly you can run it from a command line by executing:
```shell
mvn test "-Dtest=CarRentalTask1Test"
```
### Task 2
Our community is growing we have more and more requests. Let's make our `CarRentalService` thread safe (**Eventually Consistent** solution is fine).
In this task you are asked to implement the `com.acme.rental.CarRentalServiceImpl` class to be thread safe.

### Task 3
Some clients requested a new feature. They want to reserve a car for a specific time period before renting it.

In this task you are asked to implement 2 methods in the `com.acme.rental.CarRentalServiceImpl` class.
- `reserveCar` - This method should reserve a car for a client for specific time period (measured in days). 
The method should return the car that was reserved.
- `cancelCarReservation` - This method should cancel a car reservation for a client. The method should return the car that was reserved.
If the car is already rented, the method should return null.
